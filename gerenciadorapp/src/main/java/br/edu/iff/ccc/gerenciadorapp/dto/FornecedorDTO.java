package br.edu.iff.ccc.gerenciadorapp.dto;

import jakarta.validation.constraints.NotBlank;

public class FornecedorDTO {

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Contato não pode estar vazio")
    private String contato;

    public FornecedorDTO() {}

    public FornecedorDTO(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
