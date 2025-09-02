package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final List<Produto> produtos = new ArrayList<>();
    private long nextId = 1;

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public Produto salvar(Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }

    public Optional<Produto> buscarPorId(long id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Produto atualizar(long id, Produto produtoAtualizado) {
        return buscarPorId(id).map(existente -> {
            existente.setNome(produtoAtualizado.getNome());
            existente.setDescricao(produtoAtualizado.getDescricao());
            existente.setQuantidade(produtoAtualizado.getQuantidade());
            existente.setPreco(produtoAtualizado.getPreco());
            existente.setFornecedor(produtoAtualizado.getFornecedor());
            return existente;
        }).orElse(null);
    }

    public boolean deletar(long id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
}
