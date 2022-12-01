package com.victormora.labmarket.service.interfaces;

import java.util.List;

import com.victormora.labmarket.model.Produto;

public interface ProdutoService extends DefaultCrud<Produto> {
    String valorTotal();
    List<Produto> produtosPagos();
}
