package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.UserDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.listarTodos());
        return "listUsers";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "createUserForm";
    }

    @PostMapping("/novo")
    public String salvarNovoUsuario(@ModelAttribute("userDTO") @Valid UserDTO userDTO, 
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createUserForm";
        }

        // Converte DTO para entidade
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        userService.salvar(user);

        return "redirect:/usuarios";
    }

    @GetMapping("/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/usuarios"; // Redireciona se não encontrar
        }
        model.addAttribute("usuario", usuario);
        return "userDetailHome";
    }

    @GetMapping("/{id}/editar")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/usuarios";
        }

        // Popula DTO com os dados atuais
        UserDTO userDTO = new UserDTO(usuario.getName(), usuario.getEmail());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("usuarioId", id);

        return "usuarios/editar";
    }

    @PostMapping("/{id}/editar")
    public String salvarEdicaoUsuario(@PathVariable Long id, 
                                      @ModelAttribute("userDTO") @Valid UserDTO userDTO, 
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarioId", id);
            return "usuarios/editar";
        }

        User usuarioAtualizado = new User();
        usuarioAtualizado.setName(userDTO.getName());
        usuarioAtualizado.setEmail(userDTO.getEmail());

        userService.atualizar(id, usuarioAtualizado);

        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/deletar")
    public String deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return "redirect:/usuarios";
    }
}
