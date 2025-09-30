package br.edu.iff.ccc.gerenciadorapp.services;

import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import br.edu.iff.ccc.gerenciadorapp.exceptions.FornecedorNaoEncontradoException; // Importar
import br.edu.iff.ccc.gerenciadorapp.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNaoEncontradoException("Fornecedor com ID " + id + " não encontrado."));
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        if (fornecedorRepository.existsByNome(fornecedor.getNome())) {
            throw new IllegalStateException("Já existe um fornecedor cadastrado com o nome: " + fornecedor.getNome());
        }
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {

        Fornecedor existente = buscarPorId(id);

        existente.setNome(fornecedorAtualizado.getNome());
        existente.setContato(fornecedorAtualizado.getContato());

        return fornecedorRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new FornecedorNaoEncontradoException("Fornecedor com ID " + id + " não encontrado para deleção.");
        }
        fornecedorRepository.deleteById(id);
    }
}