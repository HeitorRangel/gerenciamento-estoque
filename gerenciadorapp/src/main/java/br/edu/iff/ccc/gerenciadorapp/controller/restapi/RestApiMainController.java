package br.edu.iff.ccc.gerenciadorapp.controller.restapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "API", description = "Endpoints gerais da API")
public class RestApiMainController {

    public static class ApiStatusDTO {
        private String status;
        private String version;

        public ApiStatusDTO(String status, String version) {
            this.status = status;
            this.version = version;
        }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }
    }

    @Operation(summary = "Status da API", description = "Retorna o status da API e versão")
    @GetMapping
    public ResponseEntity<ApiStatusDTO> getApiStatus() {
        ApiStatusDTO statusDTO = new ApiStatusDTO(
            "Sistema de Gerenciamento de Estoque ativo",
            "v1.0"
        );
        return ResponseEntity.ok(statusDTO);
    }
}
