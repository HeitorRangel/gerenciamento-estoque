package br.edu.iff.ccc.gerenciadorapp.repository;

import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByNome(String nome);

    boolean existsByNome(String nome);

    List<Fornecedor> findByNomeContainingIgnoreCase(String termoBusca);
}