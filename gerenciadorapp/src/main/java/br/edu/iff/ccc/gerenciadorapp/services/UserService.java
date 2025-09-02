package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> usuarios = new ArrayList<>();

    public UserService() {
        // Usuário padrão (mockado)
        usuarios.add(new User(1L, "Usuário Padrão", "usuario@teste.com"));
    }

    public List<User> listarTodos() {
        return usuarios;
    }

    public Optional<User> buscarPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public void salvar(User user) {
        usuarios.add(user);
    }

    public void atualizar(Long id, User userAtualizado) {
        usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .ifPresent(u -> {
                u.setName(userAtualizado.getName());
                u.setEmail(userAtualizado.getEmail());
            });
    }

    public void deletar(Long id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}
