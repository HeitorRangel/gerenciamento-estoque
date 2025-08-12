package br.edu.iff.ccc.gerenciadorapp.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorViewController {

    @GetMapping
    public String listarFornecedores(Model model) {
        // model.addAttribute("fornecedores", ...);
        return "fornecedores/lista";
    }

    @GetMapping("/novo")
    public String novoFornecedorForm(Model model) {
        // model.addAttribute("fornecedor", new Fornecedor());
        return "fornecedores/novo";
    }

    @GetMapping("/{id}")
    public String detalhesFornecedor(@PathVariable Long id, Model model) {
        // model.addAttribute("fornecedor", ...);
        return "fornecedores/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarFornecedorForm(@PathVariable Long id, Model model) {
        // model.addAttribute("fornecedor", ...);
        return "fornecedores/editar";
    }
}