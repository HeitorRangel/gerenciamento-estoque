package br.edu.iff.ccc.gerenciadorapp.controller.view;

import br.edu.iff.ccc.gerenciadorapp.dto.MovimentoEstoqueDTO;
import br.edu.iff.ccc.gerenciadorapp.entities.Produto;
import br.edu.iff.ccc.gerenciadorapp.entities.User;
import br.edu.iff.ccc.gerenciadorapp.entities.TipoMovimento;
import br.edu.iff.ccc.gerenciadorapp.entities.MovimentoEstoque;
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
        model.addAttribute("movimentoDTO", new MovimentoEstoqueDTO());
        model.addAttribute("produtos", produtoService.listarTodos());
        model.addAttribute("usuarios", userService.listarTodos());
        model.addAttribute("tiposMovimento", TipoMovimento.values());
        return "movimentos/formulario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("movimentoDTO") MovimentoEstoqueDTO dto,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoService.listarTodos());
            model.addAttribute("usuarios", userService.listarTodos());
            model.addAttribute("tiposMovimento", TipoMovimento.values());
            return "movimentos/formulario";
        }

        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        User usuario = userService.buscarPorId(dto.getUsuarioId());

        if (produto == null || usuario == null) {
            return "redirect:/movimentos";
        }

        movimentoEstoqueService.registrarMovimento(
                produto,
                dto.getQuantidade(),
                dto.getTipo(),
                usuario
        );

        return "redirect:/movimentos";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        MovimentoEstoque movimento = movimentoEstoqueService.buscarPorId(id);
        if (movimento == null) {
            return "redirect:/movimentos";
        }
        model.addAttribute("movimento", movimento);
        return "movimentos/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        MovimentoEstoque movimento = movimentoEstoqueService.buscarPorId(id);
        if (movimento == null) {
            return "redirect:/movimentos";
        }

        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setProdutoId(movimento.getProduto().getId());
        dto.setUsuarioId(movimento.getUsuario().getId());
        dto.setQuantidade(movimento.getQuantidade());
        dto.setTipo(movimento.getTipo());

        model.addAttribute("movimentoDTO", dto);
        model.addAttribute("movimentoId", id);
        model.addAttribute("produtos", produtoService.listarTodos());
        model.addAttribute("usuarios", userService.listarTodos());
        model.addAttribute("tiposMovimento", TipoMovimento.values());

        return "movimentos/formulario";
    }

    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("movimentoDTO") MovimentoEstoqueDTO dto,
                            BindingResult result,
                            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoService.listarTodos());
            model.addAttribute("usuarios", userService.listarTodos());
            model.addAttribute("tiposMovimento", TipoMovimento.values());
            model.addAttribute("movimentoId", id);
            return "movimentos/formulario";
        }

        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        User usuario = userService.buscarPorId(dto.getUsuarioId());

        if (produto == null || usuario == null) {
            return "redirect:/movimentos";
        }

        movimentoEstoqueService.atualizarMovimento(id, produto, dto.getQuantidade(), dto.getTipo(), usuario);

        return "redirect:/movimentos";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        movimentoEstoqueService.deletarPorId(id);
        return "redirect:/movimentos";
    }
}
