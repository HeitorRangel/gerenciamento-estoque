package br.edu.iff.ccc.webbege2.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @GetMapping
    public String listarUsuarios(Model model) {
        // model.addAttribute("usuarios", ...);
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        // model.addAttribute("usuario", new Usuario());
        return "usuarios/novo";
    }

    @GetMapping("/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        // model.addAttribute("usuario", ...);
        return "usuarios/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        // model.addAttribute("usuario", ...);
        return "usuarios/editar";
    }
}