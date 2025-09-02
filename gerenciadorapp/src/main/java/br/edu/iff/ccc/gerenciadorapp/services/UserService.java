package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> usuarios = new ArrayList<>();
    private long nextId = 1;

    public UserService() {
        // Usuários mocados
        usuarios.add(new User(nextId++, "Admin", "admin@teste.com"));
        usuarios.add(new User(nextId++, "Gerente Estoque", "gerente@teste.com"));
    }

    public List<User> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public User buscarPorId(Long id) {
        for (User u : usuarios) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    public void salvar(User user) {
        user.setId(nextId++);
        usuarios.add(user);
    }

    public void atualizar(Long id, User userAtualizado) {
        User existente = buscarPorId(id);
        if (existente != null) {
            existente.setName(userAtualizado.getName());
            existente.setEmail(userAtualizado.getEmail());
        }
    }

    public void deletar(Long id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}
