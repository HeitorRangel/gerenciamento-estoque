package br.edu.iff.ccc.gerenciadorapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "fornecedor_gerenciador")
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Contato não pode estar vazio")
    private String contato;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    public Fornecedor() {}

    public Fornecedor(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", contato='" + contato + '\'' +
                ", qtdProdutos=" + (produtos != null ? produtos.size() : 0) +
                '}';
    }
}
