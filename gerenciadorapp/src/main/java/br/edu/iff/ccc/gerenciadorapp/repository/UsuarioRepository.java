package br.edu.iff.ccc.gerenciadorapp.repository;

import br.edu.iff.ccc.gerenciadorapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);
}