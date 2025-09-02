package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Produto buscarPorId(long id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public Produto atualizar(long id, Produto produtoAtualizado) {
        Produto existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(produtoAtualizado.getNome());
            existente.setDescricao(produtoAtualizado.getDescricao());
            existente.setQuantidade(produtoAtualizado.getQuantidade());
            existente.setPreco(produtoAtualizado.getPreco());
            existente.setFornecedor(produtoAtualizado.getFornecedor());
        }
        return existente;
    }

    public boolean deletar(long id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
}
