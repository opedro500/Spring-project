package com.imd.projeto2.DTO;

import com.imd.projeto2.models.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record ProdutoDTO(
        @NotBlank
        String nome,

        @NotBlank
        String marca,

        @NotNull
        @Past
        LocalDate data_fabricacao,

        LocalDate data_validade,

        @NotNull
        Tipo tipo,

        @NotBlank
        String lote) {
}
