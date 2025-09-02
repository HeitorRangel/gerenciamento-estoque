package br.edu.iff.ccc.gerenciadorapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email inválido")
    private String email;

    public UserDTO() {}

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
