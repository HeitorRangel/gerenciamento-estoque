package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.ProdutoDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.services.FornecedorService;
import br.edu.iff.ccc.gerenciadorapp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        return "produtos/novo";
    }

    @PostMapping("/novo")
    public String salvarNovoProduto(@ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fornecedores", fornecedorService.listarTodos());
            return "produtos/novo";
        }

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPreco(produtoDTO.getPreco());

        // Associa fornecedor se existir
        Fornecedor fornecedor = fornecedorService.buscarPorId(produtoDTO.getFornecedorId()).orElse(null);
        produto.setFornecedor(fornecedor);

        produtoService.salvar(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/{id}")
    public String detalhesProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id).orElse(null);
        if (produto == null) {
            return "redirect:/produtos";
        }
        model.addAttribute("produto", produto);
        return "produtos/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id).orElse(null);
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
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        model.addAttribute("produtoId", id);

        return "produtos/editar";
    }

    @PostMapping("/{id}/editar")
    public String salvarEdicaoProduto(@PathVariable Long id,
                                      @ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fornecedores", fornecedorService.listarTodos());
            model.addAttribute("produtoId", id);
            return "produtos/editar";
        }

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome(produtoDTO.getNome());
        produtoAtualizado.setDescricao(produtoDTO.getDescricao());
        produtoAtualizado.setQuantidade(produtoDTO.getQuantidade());
        produtoAtualizado.setPreco(produtoDTO.getPreco());
        produtoAtualizado.setFornecedor(
                fornecedorService.buscarPorId(produtoDTO.getFornecedorId()).orElse(null)
        );

        produtoService.atualizar(id, produtoAtualizado);

        return "redirect:/produtos";
    }

    @PostMapping("/{id}/deletar")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }
}
