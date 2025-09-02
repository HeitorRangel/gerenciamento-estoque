package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private long nextId = 1;

    public List<Fornecedor> listarTodos() {
        return new ArrayList<>(fornecedores);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        fornecedor.setId(nextId++);
        fornecedores.add(fornecedor);
        return fornecedor;
    }

    public Optional<Fornecedor> buscarPorId(long id) {
        return fornecedores.stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    public Fornecedor atualizar(long id, Fornecedor fornecedorAtualizado) {
        return buscarPorId(id).map(existente -> {
            existente.setNome(fornecedorAtualizado.getNome());
            existente.setContato(fornecedorAtualizado.getContato());
            return existente;
        }).orElse(null);
    }

    public boolean deletar(long id) {
        return fornecedores.removeIf(f -> f.getId() == id);
    }
}
