package com.imd.projeto2.DTO;

import com.imd.projeto2.models.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteDTO(
        @NotBlank
        String nome,

        @NotBlank
        @CPF
        String cpf,

        @NotNull
        Genero genero,

        @NotNull
        @Past
        LocalDate data_nascimento) {
}

