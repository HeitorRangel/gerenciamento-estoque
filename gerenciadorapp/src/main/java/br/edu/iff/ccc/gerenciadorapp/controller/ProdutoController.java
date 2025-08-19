package br.edu.iff.ccc.gerenciadorapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduto(@PathVariable Long id) {
        String produto = String.format("Produto com ID %d: Nome=Exemplo, Descrição=Produto de exemplo, Preço=100.00", id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<String> criarProduto(
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam Double preco) {
        System.out.printf("Produto recebido: Nome=%s, Descrição=%s, Preço=%.2f%n", nome, descricao, preco);
        return ResponseEntity.ok("Produto cadastrado com sucesso!");
    }
}