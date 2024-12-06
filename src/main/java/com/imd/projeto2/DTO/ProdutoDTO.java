package com.imd.projeto2.DTO;

import com.imd.projeto2.models.Tipo;

import java.time.LocalDate;

public record ProdutoDTO(
        String nome,
        String marca,
        LocalDate data_fabricacao,
        LocalDate data_validade,
        Tipo tipo,
        String lote) {
}
