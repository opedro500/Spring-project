package com.imd.projeto2.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoDTO(
        @NotBlank
        String codigo,

        @NotNull
        Long id_cliente,

        @NotNull
        List<Long> ids_produtos
) {
}
