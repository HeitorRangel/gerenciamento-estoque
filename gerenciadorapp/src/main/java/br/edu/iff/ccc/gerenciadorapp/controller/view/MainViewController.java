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

    /**
     * Exibe a página principal (home).
     */
    @GetMapping
    public String exibirHome(Model model) {
        model.addAttribute("pageTitle", "Home - GerenciadorApp");
        return "index"; // aponta para templates/index.html
    }

    /**
     * Exibe os detalhes de um usuário específico.
     */
    @GetMapping("/user/{id}")
    public String exibirUsuario(@PathVariable Long id, Model model) {
    User usuario = new User();
    usuario.setId(id);
    usuario.setName("Usuário Teste");
    usuario.setEmail("teste@email.com");

    model.addAttribute("usuario", usuario);
    return "usuarios/detalhes";
    }

}
