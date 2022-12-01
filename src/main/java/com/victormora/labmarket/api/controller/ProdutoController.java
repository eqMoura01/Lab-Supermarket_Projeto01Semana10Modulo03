package com.victormora.labmarket.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victormora.labmarket.model.Produto;
import com.victormora.labmarket.service.interfaces.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    // Injeção de dependencias

    @Autowired
    private ProdutoService produtoService;

    // CRUD

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.salvar(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(this.produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.produtoService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(this.produtoService.atualizar(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    // Metodos

    @GetMapping("/valorTotal")
    public ResponseEntity<String> total() {
        return ResponseEntity.ok(this.produtoService.valorTotal());
    }

    @GetMapping("/produtosPagos")
    public ResponseEntity<List<Produto>> produtosPagos() {
        return ResponseEntity.ok(this.produtoService.produtosPagos());
    }
}
