package br.edu.iff.ccc.gerenciadorapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "produto_gerenciador")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Descrição não pode estar vazia")
    @Column(nullable = false)
    private String descricao;

    @PositiveOrZero(message = "Quantidade não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidade;

    @Positive(message = "Preço deve ser maior que zero")
    @Column(nullable = false)
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentoEstoque> movimentos = new ArrayList<>();

    public List<MovimentoEstoque> getMovimentos() { return movimentos; }
    public void setMovimentos(List<MovimentoEstoque> movimentos) { this.movimentos = movimentos; }


    public Produto() {}
    public Produto(Long id, String nome, String descricao,
                   Integer quantidade, Float preco, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Float getPreco() { return preco; }
    public void setPreco(Float preco) { this.preco = preco; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", fornecedor=" + (fornecedor != null ? fornecedor.getNome() : "null") +
                '}';
    }
}
