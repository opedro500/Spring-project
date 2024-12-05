package com.imd.projeto1.DTO;

import com.imd.projeto1.model.Tipo;

import java.time.LocalDate;

public record ProdutoDTO(
        String nome,
        String marca,
        LocalDate data_fabricacao,
        LocalDate data_validade,
        Tipo tipo,
        String lote) {
}
