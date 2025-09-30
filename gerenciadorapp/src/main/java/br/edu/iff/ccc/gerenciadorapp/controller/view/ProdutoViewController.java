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

    // Listagem de todos os produtos
    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/listar";
    }

    // Exibe o formulário para criar novo produto
    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        return "produtos/formulario";
    }

    // Salva o novo produto no banco
    @PostMapping("/novo")
    public String salvarNovoProduto(@ModelAttribute ProdutoDTO produtoDTO) {
        Produto produto = converterDTOParaProduto(produtoDTO);
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    // Exibe o formulário de edição
    @GetMapping("/{id}/editar")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            return "redirect:/produtos";
        }

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

    // Salva as alterações feitas em um produto existente
    @PostMapping("/{id}/editar")
    public String salvarEdicaoProduto(@PathVariable Long id,
                                      @ModelAttribute ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = converterDTOParaProduto(produtoDTO);
        produtoService.atualizar(id, produtoAtualizado);
        return "redirect:/produtos";
    }

    // Exclui um produto do banco
    @PostMapping("/{id}/deletar")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }

    // Conversão DTO -> Entidade
    private Produto converterDTOParaProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setQuantidade(dto.getQuantidade());
        produto.setPreco(dto.getPreco());

        if (dto.getFornecedorId() != null) {
            produto.setFornecedor(
                    fornecedorService.buscarPorId(dto.getFornecedorId())
            );
        }

        return produto;
    }
}
