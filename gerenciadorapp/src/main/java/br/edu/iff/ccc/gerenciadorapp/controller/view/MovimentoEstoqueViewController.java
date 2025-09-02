package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.MovimentoEstoqueDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.entities.TipoMovimento;
import br.edu.iff.ccc.gerenciadorapp.services.MovimentoEstoqueService;
import br.edu.iff.ccc.gerenciadorapp.services.ProdutoService;
import br.edu.iff.ccc.gerenciadorapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movimentos")
public class MovimentoEstoqueViewController {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("movimentos", movimentoEstoqueService.listarTodos());
        return "movimentos/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("movimentoEstoqueDTO", new MovimentoEstoqueDTO());
        model.addAttribute("produtos", produtoService.listarTodos());
        model.addAttribute("usuarios", userService.listarTodos());
        model.addAttribute("tiposMovimento", TipoMovimento.values());
        return "movimentos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute MovimentoEstoqueDTO dto,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoService.listarTodos());
            model.addAttribute("usuarios", userService.listarTodos());
            model.addAttribute("tiposMovimento", TipoMovimento.values());
            return "movimentos/form";
        }

        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        User usuario = userService.buscarPorId(dto.getUsuarioId());

        if (produto == null || usuario == null) {
            return "redirect:/movimentos"; // redireciona se não encontrado
        }

        movimentoEstoqueService.registrarMovimento(
                produto,
                dto.getQuantidade(),
                dto.getTipo(),
                usuario
        );

        return "redirect:/movimentos";
    }
}
