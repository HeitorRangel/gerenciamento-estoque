package br.edu.iff.ccc.gerenciadorapp.services;
import br.edu.iff.ccc.gerenciadorapp.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User createUser(long id, String name, String email) {
        System.err.println("Usuario criado com sucesso: " + name);
        return new User(id, name, email);
    }

    public User getUser(long id) {

        if (id == 1L){
            return new User(1L,  "Usuario Teste",  "emailteste@email.com");      
        }else if (id == 2L) {
            return new User(2L,  "Usuario Teste 2", "emailteste2@email.com");
    }
    return null;
    }
    
    public User updateUser(long id, String name, String email) {
        User user = getUser(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
        }
        return user;
    }

    public void deleteUser(long id) {
        System.out.println("Usuario com id " + id + " deletado.");
    }

    public List<User> listUsers() {
        // Simulando uma lista de usuários
        User user1 = new User(1L, "Usuario Teste", "emailteste@email.com");
        User user2 = new User(2L, "Usuario Teste 2", "emailteste2@email.com");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }
}   