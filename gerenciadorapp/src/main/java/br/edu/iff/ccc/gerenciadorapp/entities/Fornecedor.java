package br.edu.iff.ccc.gerenciadorapp.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "fornecedor-gerenciador")
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotEmpty(message = "Não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Não pode ser vazio")
    private String contato;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", contato='" + contato + '\'' +
                '}';
    }
}