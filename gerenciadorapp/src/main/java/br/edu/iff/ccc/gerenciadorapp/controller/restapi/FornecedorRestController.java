package br.edu.iff.ccc.gerenciadorapp.controller.restapi;

import br.edu.iff.ccc.gerenciadorapp.dto.FornecedorDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import br.edu.iff.ccc.gerenciadorapp.services.FornecedorService;
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
@RequestMapping("/api/v1/fornecedores")
@Tag(name = "Fornecedores", description = "Endpoints para gerenciamento de fornecedores")
public class FornecedorRestController {

    @Autowired
    private FornecedorService fornecedorService;

    @Operation(summary = "Listar Fornecedores", description = "Lista todos os fornecedores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de fornecedores retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }

    @Operation(summary = "Buscar Fornecedor por ID", description = "Retorna um fornecedor pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Fornecedor encontrado")
    @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(fornecedor);
    }

    @Operation(summary = "Criar Fornecedor", description = "Cria um novo fornecedor")
    @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou fornecedor já existe")
    @PostMapping
    public ResponseEntity<Fornecedor> criarFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = converterDTOParaFornecedor(fornecedorDTO);
        Fornecedor salvo = fornecedorService.salvar(fornecedor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(salvo);
    }

    @Operation(summary = "Atualizar Fornecedor", description = "Atualiza um fornecedor existente")
    @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id,
                                                          @Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedorAtualizado = converterDTOParaFornecedor(fornecedorDTO);
        Fornecedor atualizado = fornecedorService.atualizar(id, fornecedorAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar Fornecedor", description = "Remove um fornecedor pelo ID")
    @ApiResponse(responseCode = "204", description = "Fornecedor removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Método privado para converter DTO -> Entidade
    private Fornecedor converterDTOParaFornecedor(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setContato(dto.getContato());
        return fornecedor;
    }
}
