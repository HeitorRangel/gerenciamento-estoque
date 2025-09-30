package br.edu.iff.ccc.gerenciadorapp.controller.restapi;

import br.edu.iff.ccc.gerenciadorapp.dto.MovimentoEstoqueDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.MovimentoEstoque;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.services.MovimentoEstoqueService;
import br.edu.iff.ccc.gerenciadorapp.services.ProdutoService;
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
@RequestMapping("/api/v1/movimentos")
@Tag(name = "Movimentos de Estoque", description = "Endpoints para gerenciamento de movimentações de estoque")
public class MovimentoEstoqueRestController {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar Movimentos", description = "Lista todas as movimentações de estoque registradas")
    @ApiResponse(responseCode = "200", description = "Lista de movimentos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<MovimentoEstoque>> listarTodos() {
        return ResponseEntity.ok(movimentoEstoqueService.listarTodos());
    }

    @Operation(summary = "Buscar Movimento por ID", description = "Retorna uma movimentação de estoque pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Movimento encontrado")
    @ApiResponse(responseCode = "404", description = "Movimento não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<MovimentoEstoque> buscarPorId(@PathVariable Long id) {
        MovimentoEstoque movimento = movimentoEstoqueService.buscarPorId(id);
        return ResponseEntity.ok(movimento);
    }

    @Operation(summary = "Registrar Movimento", description = "Cria uma nova movimentação de estoque")
    @ApiResponse(responseCode = "201", description = "Movimento registrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<MovimentoEstoque> criarMovimento(@Valid @RequestBody MovimentoEstoqueDTO dto) {
        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        User usuario = userService.buscarPorId(dto.getUsuarioId());

        MovimentoEstoque salvo = movimentoEstoqueService.registrarMovimento(
                produto,
                dto.getQuantidade(),
                dto.getTipo(),
                usuario
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(salvo);
    }

    @Operation(summary = "Atualizar Movimento", description = "Atualiza uma movimentação existente")
    @ApiResponse(responseCode = "200", description = "Movimento atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Movimento não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<MovimentoEstoque> atualizarMovimento(@PathVariable Long id,
                                                               @Valid @RequestBody MovimentoEstoqueDTO dto) {
        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        User usuario = userService.buscarPorId(dto.getUsuarioId());

        MovimentoEstoque atualizado = movimentoEstoqueService.atualizarMovimento(
                id,
                produto,
                dto.getQuantidade(),
                dto.getTipo(),
                usuario
        );

        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar Movimento", description = "Remove uma movimentação de estoque pelo ID")
    @ApiResponse(responseCode = "204", description = "Movimento removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Movimento não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMovimento(@PathVariable Long id) {
        movimentoEstoqueService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
