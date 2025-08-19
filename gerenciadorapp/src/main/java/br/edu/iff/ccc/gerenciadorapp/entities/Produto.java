package br.edu.iff.ccc.gerenciadorapp.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "produto-gerenciador")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotEmpty(message = "Não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Não pode ser vazio")
    private String descricao;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}   