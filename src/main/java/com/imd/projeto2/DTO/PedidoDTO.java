package com.imd.projeto2.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
        @NotNull
        @NotBlank
        String codigo
) {
}
