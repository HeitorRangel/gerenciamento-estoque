package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    public Fornecedor createFornecedor(long id, String nome, String contato) {
        System.err.println("Fornecedor criado com sucesso: " + nome);
        return new Fornecedor(id, nome, contato);
    }

    public Fornecedor getFornecedor(long id) {

        if (id == 1L){
            return new Fornecedor(1L,  "Fornecedor Teste",  "contatoteste");      
        }else if (id == 2L) {
            return new Fornecedor(2L,  "Fornecedor Teste 2", "contatoteste2");
    }
    return null;
    }
    
    public Fornecedor updateFornecedor(long id, String nome, String contato) {
        Fornecedor fornecedor = getFornecedor(id);
        if (fornecedor != null) {
            fornecedor.setNome(nome);
            fornecedor.setContato(contato);
        }
        return fornecedor;
    }

    public void deleteFornecedor(long id) {
        System.out.println("Fornecedor com id " + id + " deletado.");
    }

    public List<Fornecedor> listFornecedores() {
        // Simulando uma lista de fornecedores
        Fornecedor fornecedor1 = new Fornecedor(1L, "Fornecedor Teste", "contatoteste");
        Fornecedor fornecedor2 = new Fornecedor(2L, "Fornecedor Teste 2", "contatoteste2");
        List<Fornecedor> fornecedores = new ArrayList<>();
        fornecedores.add(fornecedor1);
        fornecedores.add(fornecedor2);
        return fornecedores;
    }
}