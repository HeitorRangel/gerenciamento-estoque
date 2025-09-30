package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.exceptions.ProdutoNaoEncontradoException; // Importar exceção
import br.edu.iff.ccc.gerenciadorapp.exceptions.RegraDeNegocioException;
import br.edu.iff.ccc.gerenciadorapp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado."));
    }

    public Produto salvar(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new RegraDeNegocioException("Já existe um produto cadastrado com o nome: " + produto.getNome());
        }
        return produtoRepository.save(produto);
    }


    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setFornecedor(produtoAtualizado.getFornecedor());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado para deleção.");
        }
        produtoRepository.deleteById(id);
    }
}