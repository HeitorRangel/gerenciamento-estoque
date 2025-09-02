package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.FornecedorDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Fornecedor;
import br.edu.iff.ccc.gerenciadorapp.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorViewController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String listarFornecedores(Model model) {
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        return "fornecedores/lista";
    }

    @GetMapping("/novo")
    public String novoFornecedorForm(Model model) {
        model.addAttribute("fornecedorDTO", new FornecedorDTO());
        return "fornecedores/novo";
    }

    @PostMapping("/novo")
    public String salvarNovoFornecedor(@ModelAttribute("fornecedorDTO") @Valid FornecedorDTO fornecedorDTO,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "fornecedores/novo";
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setContato(fornecedorDTO.getContato());

        fornecedorService.salvar(fornecedor);

        return "redirect:/fornecedores";
    }

    @GetMapping("/{id}")
    public String detalhesFornecedor(@PathVariable Long id, Model model) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id).orElse(null);
        if (fornecedor == null) {
            return "redirect:/fornecedores";
        }
        model.addAttribute("fornecedor", fornecedor);
        return "fornecedores/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarFornecedorForm(@PathVariable Long id, Model model) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id).orElse(null);
        if (fornecedor == null) {
            return "redirect:/fornecedores";
        }

        FornecedorDTO fornecedorDTO = new FornecedorDTO(fornecedor.getNome(), fornecedor.getContato());

        model.addAttribute("fornecedorDTO", fornecedorDTO);
        model.addAttribute("fornecedorId", id);

        return "fornecedores/editar";
    }

    @PostMapping("/{id}/editar")
    public String salvarEdicaoFornecedor(@PathVariable Long id,
                                        @ModelAttribute("fornecedorDTO") @Valid FornecedorDTO fornecedorDTO,
                                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fornecedorId", id);
            return "fornecedores/editar";
        }

        Fornecedor fornecedorAtualizado = new Fornecedor();
        fornecedorAtualizado.setNome(fornecedorDTO.getNome());
        fornecedorAtualizado.setContato(fornecedorDTO.getContato());

        fornecedorService.atualizar(id, fornecedorAtualizado);

        return "redirect:/fornecedores";
    }

    @PostMapping("/{id}/deletar")
    public String deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return "redirect:/fornecedores";
    }
}
