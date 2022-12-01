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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victormora.labmarket.model.Categoria;
import com.victormora.labmarket.service.interfaces.CategoriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.salvar(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(this.categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarById(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoriaService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarById(@PathVariable Long id) {
        this.categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
