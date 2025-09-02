package br.edu.iff.ccc.gerenciadorapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProdutoDTO {

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String descricao;

    @PositiveOrZero(message = "Quantidade não pode ser negativa")
    private Integer quantidade;

    @Positive(message = "Preço deve ser maior que zero")
    private Float preco;

    private Long fornecedorId; // Id do fornecedor, será usado para associar

    public ProdutoDTO() {}

    public ProdutoDTO(String nome, String descricao, Integer quantidade, Float preco, Long fornecedorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedorId = fornecedorId;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Float getPreco() { return preco; }
    public void setPreco(Float preco) { this.preco = preco; }

    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }
}
