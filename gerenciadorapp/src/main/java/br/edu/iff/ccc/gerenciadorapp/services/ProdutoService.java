package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    public Produto createProduto(long id, String nome, String descricao) {
        System.err.println("Produto criado com sucesso: " + nome);
        return new Produto(id, nome, descricao);
    }

    public Produto getProduto(long id) {

        if (id == 1L){
            return new Produto(1L,  "Produto Teste",  "descricaoteste");      
        }else if (id == 2L) {
            return new Produto(2L,  "Produto Teste 2", "descricaoteste2");
    }
    return null;
    }
    
    public Produto updateProduto(long id, String nome, String descricao) {
        Produto produto = getProduto(id);
        if (produto != null) {
            produto.setNome(nome);
            produto.setDescricao(descricao);
        }
        return produto;
    }

    public void deleteProduto(long id) {
        System.out.println("Produto com id " + id + " deletado.");
    }

    public List<Produto> listProdutos() {
        // Simulando uma lista de produtos
        Produto produto1 = new Produto(1L, "Produto Teste", "descricaoteste");
        Produto produto2 = new Produto(2L, "Produto Teste 2", "descricaoteste2");
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        return produtos;
    }
}
