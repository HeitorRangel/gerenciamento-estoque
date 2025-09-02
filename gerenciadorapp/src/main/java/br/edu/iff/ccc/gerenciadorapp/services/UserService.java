package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> usuarios = new ArrayList<>();
    private long nextId = 1; // controla o próximo ID

    public UserService() {
        // Usuários mocados
        User admin = new User(nextId++, "Admin", "admin@teste.com");
        User gerente = new User(nextId++, "Gerente Estoque", "gerente@teste.com");

        usuarios.add(admin);
        usuarios.add(gerente);
    }

    public List<User> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public Optional<User> buscarPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public void salvar(User user) {
        user.setId(nextId++);
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
