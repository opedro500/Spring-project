package com.imd.projeto1.model;

import com.imd.projeto1.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_produto;
    private String nome;
    private String marca;
    private LocalDate data_fabricacao;
    private LocalDate data_validade;
    private Tipo tipo;
    private String lote;

    public void atualizarAtributosNaoNulos(ProdutoDTO produtoDTO) {
        if (produtoDTO.nome() != null) {
            this.nome = produtoDTO.nome();
        }
        if (produtoDTO.marca() != null) {
            this.marca = produtoDTO.marca();
        }
        if (produtoDTO.data_fabricacao() != null) {
            this.data_fabricacao = produtoDTO.data_fabricacao();
        }
        if (produtoDTO.data_validade() != null) {
            this.data_validade = produtoDTO.data_validade();
        }
        if (produtoDTO.tipo() != null) {
            this.tipo = produtoDTO.tipo();
        }
        if (produtoDTO.lote() != null) {
            this.lote = produtoDTO.lote();
        }
    }
}
