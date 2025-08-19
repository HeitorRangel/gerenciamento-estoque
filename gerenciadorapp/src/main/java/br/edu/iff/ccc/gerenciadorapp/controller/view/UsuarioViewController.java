package br.edu.iff.ccc.gerenciadorapp.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.listUsers());
        return "listUsers";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("usuario", new User());
        return "createUserForm";
    }

    @PostMapping("/novo")
    public String salvarNovoUsuario(@ModelAttribute User usuario, Model model) {
        userService.createUser(usuario.getId(), usuario.getName(), usuario.getEmail());
        // Redireciona para a lista de usuários ou página de sucesso
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        User usuario = userService.getUser(id);
        model.addAttribute("usuario", usuario);
        return "userDetailHome";
    }

    @GetMapping("/{id}/editar")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        // model.addAttribute("usuario", ...);
        return "usuarios/editar";
    }
}