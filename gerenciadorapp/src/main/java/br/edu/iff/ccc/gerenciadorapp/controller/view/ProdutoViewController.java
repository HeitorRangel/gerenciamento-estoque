package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.ProdutoDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.services.FornecedorService;
import br.edu.iff.ccc.gerenciadorapp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoViewController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/listar";
    }

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        return "produtos/formulario";
    }

    @PostMapping("/novo")
    public String salvarNovoProduto(@ModelAttribute ProdutoDTO produtoDTO) {
        Produto produto = converterDTOParaProduto(produtoDTO);
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) return "redirect:/produtos";

        ProdutoDTO produtoDTO = new ProdutoDTO(
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPreco(),
                produto.getFornecedor() != null ? produto.getFornecedor().getId() : null
        );

        model.addAttribute("produtoDTO", produtoDTO);
        model.addAttribute("produtoId", id);
        model.addAttribute("fornecedores", fornecedorService.listarTodos());

        return "produtos/formulario";
    }

    @PostMapping("/{id}/editar")
    public String salvarEdicaoProduto(@PathVariable Long id,
                                      @ModelAttribute ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = converterDTOParaProduto(produtoDTO);
        produtoService.atualizar(id, produtoAtualizado);
        return "redirect:/produtos";
    }

    @PostMapping("/{id}/deletar")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }

    private Produto converterDTOParaProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setQuantidade(dto.getQuantidade());
        produto.setPreco(dto.getPreco());

        if (dto.getFornecedorId() != null) {
            produto.setFornecedor(
                    fornecedorService.buscarPorId(dto.getFornecedorId()) // já retorna null se não encontrar
            );
        }

        return produto;
    }
}
