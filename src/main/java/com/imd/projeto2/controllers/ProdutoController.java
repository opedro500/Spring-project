package com.imd.projeto2.controllers;

import com.imd.projeto2.DTO.ProdutoDTO;
import com.imd.projeto2.models.ProdutoModel;
import com.imd.projeto2.repositories.ProdutoRepository;
import com.imd.projeto2.services.ClienteService;
import com.imd.projeto2.services.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @GetMapping("/resgatar")
    public ResponseEntity<Object> resgatarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProducts());
    }

    @GetMapping("/resgatar/{id}")
    public ResponseEntity<Object> resgatarProdutoPorId(@PathVariable(value = "id") Long id) {
        var product = service.getProductById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postProduct(produtoDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value = "id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        var product = service.putProduct(id, produtoDTO);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable(value = "id") Long id) {
        var product = service.deleteProduct(id);

        if (!product) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso.");
    }

}
