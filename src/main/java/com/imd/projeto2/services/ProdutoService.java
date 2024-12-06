package com.imd.projeto2.services;

import com.imd.projeto2.DTO.ProdutoDTO;
import com.imd.projeto2.models.ProdutoModel;
import com.imd.projeto2.repositories.ClienteRepository;
import com.imd.projeto2.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository repository;

    public List<ProdutoModel> getProducts() {
        return repository.findAll();
    }

    public ProdutoModel getProductById(Long id) {
        Optional<ProdutoModel> product = repository.findById(id);

        if (product.isEmpty()) {
            return null;
        }

        return product.get();
    }

    public ProdutoModel postProduct(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);

        return repository.save(produtoModel);
    }

    public ProdutoModel patchProduct(Long id, ProdutoDTO produtoDTO) {
        Optional<ProdutoModel> product = repository.findById(id);

        if (product.isEmpty()) {
            return null;
        }

        ProdutoModel productModel = product.get();
        productModel.carregarDTO(produtoDTO);

        return repository.save(productModel);
    }

    public boolean deleteProduct(Long id) {
        Optional<ProdutoModel> product = repository.findById(id);

        if (product.isEmpty()) {
            return false;
        }

        repository.delete(product.get());

        return  true;
    }

    public ProdutoRepository repository() {
        return repository;
    }
}
