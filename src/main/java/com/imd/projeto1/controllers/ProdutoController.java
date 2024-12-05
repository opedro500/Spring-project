package com.imd.projeto1.controllers;

import com.imd.projeto1.DTO.ProdutoDTO;
import com.imd.projeto1.model.ProdutoEntity;
import com.imd.projeto1.repositories.ProdutoRepository;
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
    ProdutoRepository produtoRepository;

    @GetMapping("/resgatarTodos")
    public ResponseEntity<Object> resgatarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

    @GetMapping("/resgatar/{id}")
    public ResponseEntity<Object> resgatarProdutoPorId(@PathVariable(value = "id") Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produtoEntity);

        produtoRepository.save(produtoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso.");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value = "id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        ProdutoEntity produtoEntity = produto.get();
        produtoEntity.atualizarAtributosNaoNulos(produtoDTO);

        produtoRepository.save(produtoEntity);

        return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable(value = "id") Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        produtoRepository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso.");
    }

}
