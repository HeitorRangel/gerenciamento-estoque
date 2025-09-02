package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private long nextId = 1;

    public FornecedorService() {
        // Fornecedor de exemplo
        Fornecedor exemplo = new Fornecedor(); 
        exemplo.setId(nextId++);
        exemplo.setNome("Fornecedor Exemplo");
        fornecedores.add(exemplo);
    }

    public List<Fornecedor> listarTodos() {
        return new ArrayList<>(fornecedores);
    }

    public Fornecedor buscarPorId(long id) {
        for (Fornecedor f : fornecedores) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        fornecedor.setId(nextId++);
        fornecedores.add(fornecedor);
        return fornecedor;
    }

    public Fornecedor atualizar(long id, Fornecedor fornecedorAtualizado) {
        Fornecedor existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(fornecedorAtualizado.getNome());
            existente.setContato(fornecedorAtualizado.getContato());
        }
        return existente;
    }

    public boolean deletar(long id) {
        return fornecedores.removeIf(f -> f.getId() == id);
    }
}
