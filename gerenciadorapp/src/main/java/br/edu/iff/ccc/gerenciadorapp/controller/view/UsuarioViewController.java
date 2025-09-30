package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.UserDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UserService userService;

    // Listar todos os usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        List<User> usuarios = userService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listar";
    }

    // Detalhar um usuário específico
    @GetMapping("/{id}")
    public String detalharUsuario(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/detalhes";
    }

    // Formulário para criar novo usuário
    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "usuarios/formulario";
    }

    // Salvar novo usuário
    @PostMapping("/novo")
    public String salvarNovoUsuario(@ModelAttribute @Valid UserDTO userDTO,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "usuarios/formulario";
        }

        User usuario = new User();
        usuario.setName(userDTO.getName());
        usuario.setEmail(userDTO.getEmail());

        User salvo = userService.salvar(usuario);
        return "redirect:/usuarios/" + salvo.getId();
    }

    // Formulário para editar um usuário
    @GetMapping("/{id}/editar")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(usuario.getName());
        userDTO.setEmail(usuario.getEmail());

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("id", id);
        return "usuarios/formulario";
    }

    // Atualizar usuário
    @PostMapping("/{id}/editar")
    public String atualizarUsuario(@PathVariable Long id,
                                   @ModelAttribute @Valid UserDTO userDTO,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "usuarios/formulario";
        }

        User atualizado = new User();
        atualizado.setName(userDTO.getName());
        atualizado.setEmail(userDTO.getEmail());

        userService.atualizar(id, atualizado);
        return "redirect:/usuarios/" + id;
    }

    // Excluir usuário
    @PostMapping("/{id}/deletar")
    public String deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return "redirect:/usuarios";
    }
}
