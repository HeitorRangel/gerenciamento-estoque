package br.edu.iff.ccc.gerenciadorapp.repository;

import br.edu.iff.ccc.gerenciadorapp.entities.MovimentoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
}
