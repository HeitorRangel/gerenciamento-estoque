package br.edu.iff.ccc.gerenciadorapp.controller.restapi;

import br.edu.iff.ccc.gerenciadorapp.dto.UserDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioRestController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar Usuários", description = "Lista todos os usuários cadastrados no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<User>> listarTodos() {
        List<User> usuarios = userService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Buscar Usuário por ID", description = "Retorna um usuário pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        User usuario = userService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Criar Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já em uso")
    @PostMapping
    public ResponseEntity<User> criarUsuario(@Valid @RequestBody UserDTO userDTO) {
        User usuario = new User();
        usuario.setName(userDTO.getName());
        usuario.setEmail(userDTO.getEmail());

        User salvo = userService.salvar(usuario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(salvo);
    }

    @Operation(summary = "Atualizar Usuário", description = "Atualiza um usuário existente")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já em uso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Long id,
                                                 @Valid @RequestBody UserDTO userDTO) {
        User usuario = new User();
        usuario.setName(userDTO.getName());
        usuario.setEmail(userDTO.getEmail());

        User atualizado = userService.atualizar(id, usuario);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar Usuário", description = "Remove um usuário pelo ID")
    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
