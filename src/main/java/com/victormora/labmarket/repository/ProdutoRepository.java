package com.victormora.labmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victormora.labmarket.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    boolean existsByNome(String nome);
}
