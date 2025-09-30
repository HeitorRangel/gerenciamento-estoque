package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.MovimentoEstoque;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.entities.TipoMovimento;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.exceptions.MovimentoEstoqueNaoEncontradoException;
import br.edu.iff.ccc.gerenciadorapp.repository.MovimentoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoEstoqueService {

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<MovimentoEstoque> listarTodos() {
        return movimentoEstoqueRepository.findAll();
    }

    public MovimentoEstoque buscarPorId(Long id) {
        return movimentoEstoqueRepository.findById(id)
                .orElseThrow(() -> new MovimentoEstoqueNaoEncontradoException(
                        "Movimento de estoque com ID " + id + " não encontrado"
                ));
    }

    public MovimentoEstoque registrarMovimento(Produto produto, Integer quantidade, TipoMovimento tipo, User usuario) {
        if (produto == null || usuario == null) {
            throw new IllegalArgumentException("Produto ou usuário inválido");
        }

        if (tipo == TipoMovimento.SAIDA && produto.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        // Atualiza estoque do produto
        if (tipo == TipoMovimento.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
        } else {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }

        // Aqui usamos atualizar, não salvar novo
        produtoService.atualizar(produto.getId(), produto);

        // Cria o movimento
        MovimentoEstoque movimento = new MovimentoEstoque();
        movimento.setProduto(produto);
        movimento.setQuantidade(quantidade);
        movimento.setTipo(tipo);
        movimento.setUsuario(usuario);
        movimento.setDataMovimento(LocalDateTime.now());

        return movimentoEstoqueRepository.save(movimento);
    }

    public MovimentoEstoque atualizarMovimento(Long id, Produto produto, Integer quantidade, TipoMovimento tipo, User usuario) {
        MovimentoEstoque movimento = buscarPorId(id);

        movimento.setProduto(produto);
        movimento.setQuantidade(quantidade);
        movimento.setTipo(tipo);
        movimento.setUsuario(usuario);
        movimento.setDataMovimento(LocalDateTime.now());

        return movimentoEstoqueRepository.save(movimento);
    }

    public void deletarPorId(Long id) {
        MovimentoEstoque movimento = buscarPorId(id);
        movimentoEstoqueRepository.delete(movimento);
    }
}

