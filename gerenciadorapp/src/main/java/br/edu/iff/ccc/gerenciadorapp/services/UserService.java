package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> usuarios = new ArrayList<>();
    private long nextId = 1;

    public List<User> listarTodos() {
        return new ArrayList<>(usuarios); // retorna cópia para evitar manipulação externa
    }

    public User salvar(User user) {
        user.setId(nextId++);
        usuarios.add(user);
        return user;
    }

    public Optional<User> buscarPorId(long id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public User atualizar(long id, User usuarioAtualizado) {
        return buscarPorId(id).map(existente -> {
            existente.setName(usuarioAtualizado.getName());
            existente.setEmail(usuarioAtualizado.getEmail());
            return existente;
        }).orElse(null);
    }

    public boolean deletar(long id) {
        return usuarios.removeIf(u -> u.getId() == id);
    }
}
