package com.imd.projeto1.DTO;

import com.imd.projeto1.model.Genero;

import java.time.LocalDate;

public record ClienteDTO(
        String nome,
        String cpf,
        Genero genero,
        LocalDate data_nascimento) {
}

