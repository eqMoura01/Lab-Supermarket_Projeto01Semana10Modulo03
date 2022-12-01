package com.victormora.labmarket.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victormora.labmarket.model.Produto;
import com.victormora.labmarket.repository.ProdutoRepository;
import com.victormora.labmarket.service.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(Produto object) {
        validaNome(object.getNome());
        return this.produtoRepository.save(object);
    }

    @Override
    public Produto atualizar(Produto object) {

        Produto produtoPesquisado = buscar(object.getId());

        if (Objects.nonNull(object)) {
            BeanUtils.copyProperties(object, produtoPesquisado, "id");
            validaNome(object.getNome());
            this.produtoRepository.save(object);
        }

        return null;
    }

    @Override
    public Produto buscar(Long id) {
        Optional<Produto> produtoPesquisado = this.produtoRepository.findById(id);
        if (produtoPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um produto com o Id " + id);
        }
        return produtoPesquisado.get();
    }

    @Override
    public List<Produto> listar() {
        return this.produtoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        this.produtoRepository.deleteById(id);
    }

    @Override
    public String valorTotal() {
        List<Produto> produtos = listar();
        Double total = 0d;

        DecimalFormat df = new DecimalFormat("0.00");

        for (Produto produto : produtos) {
            if (produto.getPago() == true) {
                total += produto.getValor();
            }
        }
        return "O total da compra foi de " + df.format(total);
    }

    private void validaNome(String nome) {
        if (this.produtoRepository.existsByNome(nome)) {
            throw new EntityExistsException("Já existe um produto com o nome " + nome.toUpperCase());
        }
    }

    @Override
    public List<Produto> produtosPagos() {
        List<Produto> produtos = listar();
        List<Produto> produtosPagos = new ArrayList<Produto>();

        for (Produto produto : produtos) {
            if (produto.getPago() == true) {
                produtosPagos.add(produto);
            }
        }

        if (produtosPagos.size() < 1) {
            throw new EntityNotFoundException("Não foi encontrado nenhum produto pago.");
        }

        return produtosPagos;
    }

}
