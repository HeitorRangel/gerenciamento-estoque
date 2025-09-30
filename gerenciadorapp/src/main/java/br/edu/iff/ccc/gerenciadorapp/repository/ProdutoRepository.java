package br.edu.iff.ccc.gerenciadorapp.repository;

import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String nome);

    boolean existsByNome(String nome);

    List<Produto> findByNomeContainingIgnoreCase(String termoBusca);

    List<Produto> findByFornecedorId(Long fornecedorId);

    List<Produto> findByQuantidadeLessThan(int quantidadeMaxima);
}