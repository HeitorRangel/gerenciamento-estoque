package br.edu.iff.ccc.gerenciadorapp.dto;

import java.time.LocalDateTime;

import br.edu.iff.ccc.gerenciadorapp.entities.TipoMovimento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovimentoEstoqueDTO {

    private Long id;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "O tipo de movimento é obrigatório")
    private TipoMovimento tipo; // agora é enum, não String

    private LocalDateTime dataMovimento;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    public MovimentoEstoqueDTO() {}

    public MovimentoEstoqueDTO(Long id, Long produtoId, Integer quantidade, 
                               TipoMovimento tipo, LocalDateTime dataMovimento, Long usuarioId) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataMovimento = dataMovimento;
        this.usuarioId = usuarioId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public TipoMovimento getTipo() { return tipo; }
    public void setTipo(TipoMovimento tipo) { this.tipo = tipo; }

    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
