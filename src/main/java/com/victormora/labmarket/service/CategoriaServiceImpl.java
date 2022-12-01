package com.victormora.labmarket.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victormora.labmarket.model.Categoria;
import com.victormora.labmarket.model.Produto;
import com.victormora.labmarket.repository.CategoriaRepository;
import com.victormora.labmarket.repository.ProdutoRepository;
import com.victormora.labmarket.service.interfaces.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    // Injeção de dependencias

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // CRUD

    @Override
    public Categoria salvar(Categoria object) {
        validaNome(object.getNome());
        return this.categoriaRepository.save(object);
    }

    @Override
    public Categoria atualizar(Categoria object) {

        Categoria categoriaPesquisada = buscar(object.getId());
        if (Objects.nonNull(object)) {
            BeanUtils.copyProperties(object, categoriaPesquisada, "id");
            validaNome(object.getNome());
            this.categoriaRepository.save(object);
        }

        return null;
    }

    @Override
    public Categoria buscar(Long id) {

        Optional<Categoria> categoriaPesquisada = this.categoriaRepository.findById(id);

        if (categoriaPesquisada.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar uma categoria com o Id " + id);
        }

        return categoriaPesquisada.get();
    }

    @Override
    public List<Categoria> listar() {
        return this.categoriaRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        categoriaEmUso(id);
        this.categoriaRepository.deleteById(id);
    }

    // Metodos

    private void validaNome(String nome) {
        if (this.categoriaRepository.existsByNome(nome)) {
            throw new EntityExistsException("Já existe uma categoria com o nome " + nome.toUpperCase());
        }
    }

    private void categoriaEmUso(Long id) {
        List<Produto> produtos = this.produtoRepository.findAll();
        for (Produto produto : produtos) {
            if (produto.getCategoria().getId() == id) {
                throw new EntityExistsException("Não é possivel deletar a categoria pois está em uso");
            }
        }
    }

}
