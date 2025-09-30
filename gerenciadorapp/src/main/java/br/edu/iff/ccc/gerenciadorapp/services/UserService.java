package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.exceptions.RegraDeNegocioException;
import br.edu.iff.ccc.gerenciadorapp.exceptions.UsuarioNaoEncontradoException; // Importar a nova exceção
import br.edu.iff.ccc.gerenciadorapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<User> listarTodos() {
        return usuarioRepository.findAll();
    }

    public User buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado."));
    }

    public User salvar(User user) {
        if (usuarioRepository.existsByEmail(user.getEmail())) {
            throw new RegraDeNegocioException("Email " + user.getEmail() + " já está em uso.");
        }
        return usuarioRepository.save(user);
    }

    public User atualizar(Long id, User userAtualizado) {
        // A linha abaixo já garante que o usuário existe. Se não existir, o método para aqui.
        User existente = buscarPorId(id);

        // Atualiza os campos do usuário existente com os novos dados
        existente.setName(userAtualizado.getName());
        existente.setEmail(userAtualizado.getEmail());

        // Salva o usuário com os dados atualizados
        return usuarioRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado para deleção.");
        }
        usuarioRepository.deleteById(id);
    }

}