package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/principal")
public class MainViewController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String exibirHome() {
        return "home"; 
    }

    @GetMapping("/user/{id}")
    public String exibirUsuario(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id)
                .orElse(new User(id, "Usuário não encontrado", ""));
        model.addAttribute("usuario", usuario);
        return "userDetailHome"; 
    }
}
