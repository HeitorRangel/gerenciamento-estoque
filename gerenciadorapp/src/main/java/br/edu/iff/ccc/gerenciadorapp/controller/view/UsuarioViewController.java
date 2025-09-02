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
        return "usuarios/listar";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "usuarios/formulario";
    }

    @PostMapping("/novo")
    public String salvarNovoUsuario(@ModelAttribute @Valid UserDTO userDTO,
                                    BindingResult result) {
        if (result.hasErrors()) return "usuarios/formulario";

        User usuario = new User();
        usuario.setName(userDTO.getName());
        usuario.setEmail(userDTO.getEmail());

        userService.salvar(usuario);
        return "redirect:/usuarios/" + usuario.getId();
    }

    @GetMapping("/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id);
        if (usuario == null) return "redirect:/usuarios";

        model.addAttribute("usuario", usuario);
        return "usuarios/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        User usuario = userService.buscarPorId(id);
        if (usuario == null) return "redirect:/usuarios";

        UserDTO userDTO = new UserDTO(usuario.getName(), usuario.getEmail());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("usuarioId", id);

        return "usuarios/formulario";
    }

    @PostMapping("/{id}/editar")
    public String salvarEdicaoUsuario(@PathVariable Long id,
                                      @ModelAttribute @Valid UserDTO userDTO,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarioId", id);
            return "usuarios/formulario";
        }

        User usuarioAtualizado = new User();
        usuarioAtualizado.setName(userDTO.getName());
        usuarioAtualizado.setEmail(userDTO.getEmail());

        userService.atualizar(id, usuarioAtualizado);
        return "redirect:/usuarios/" + id;
    }

    @PostMapping("/{id}/deletar")
    public String deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/perfil")
    public String meuPerfil(Model model) {
        User admin = userService.buscarPorId(1L); // retorna null se não existir
        model.addAttribute("usuario", admin);
        return "usuarios/perfil";
    }
}
