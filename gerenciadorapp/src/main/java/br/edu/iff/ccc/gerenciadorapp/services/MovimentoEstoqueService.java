package br.edu.iff.ccc.gerenciadorapp.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.gerenciadorapp.entities.MovimentoEstoque;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.entities.TipoMovimento;
import br.edu.iff.ccc.gerenciadorapp.entities.User;

@Service
public class MovimentoEstoqueService {

    private final List<MovimentoEstoque> movimentos = new ArrayList<>();

    public List<MovimentoEstoque> listarTodos() {
        return new ArrayList<>(movimentos);
    }

    public void registrarMovimento(Produto produto, Integer quantidade, TipoMovimento tipo, User usuario) {
        if (produto == null || usuario == null) {
            throw new IllegalArgumentException("Produto ou usuário inválido");
        }

        if (tipo == TipoMovimento.SAIDA && produto.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        MovimentoEstoque mov = new MovimentoEstoque(
            produto,
            quantidade,
            tipo,
            LocalDateTime.now(),
            usuario
        );

        // adiciona na lista global de movimentos
        movimentos.add(mov);

        // adiciona também no histórico do produto
        produto.getMovimentos().add(mov);

        if (tipo == TipoMovimento.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
        } else {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }
    }
}
