package br.edu.iff.ccc.gerenciadorapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // todo movimento precisa ter um produto
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING) // salva como texto: "ENTRADA" ou "SAIDA"
    @Column(nullable = false)
    private TipoMovimento tipo;

    @Column(name = "data_movimento", nullable = false)
    private LocalDateTime dataMovimento;

    @ManyToOne(optional = false) // todo movimento deve ser feito por um usuário
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    public MovimentoEstoque() {}

    public MovimentoEstoque(Produto produto, Integer quantidade, TipoMovimento tipo,
                            LocalDateTime dataMovimento, User usuario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataMovimento = dataMovimento;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public TipoMovimento getTipo() { return tipo; }
    public void setTipo(TipoMovimento tipo) { this.tipo = tipo; }

    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }
}
