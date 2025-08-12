package br.edu.iff.ccc.gerenciadorapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduto(@PathVariable Long id) {
        String produto = String.format(
            "ID: %d\nNome: Café Gourmet\nDescrição: Café em grãos premium\nPreço: R$ 25,00", id
        );
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