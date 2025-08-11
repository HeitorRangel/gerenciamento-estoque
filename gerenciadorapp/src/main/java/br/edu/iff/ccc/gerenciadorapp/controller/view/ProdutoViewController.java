package br.edu.iff.ccc.webbege2.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoViewController {

    @GetMapping
    public String listarProdutos(Model model) {
        // model.addAttribute("produtos", ...);
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        // model.addAttribute("produto", new Produto());
        return "produtos/novo";
    }

    @GetMapping("/{id}")
    public String detalhesProduto(@PathVariable Long id, Model model) {
        // model.addAttribute("produto", ...);
        return "produtos/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        // model.addAttribute("produto", ...);
        return "produtos/editar";
    }
}