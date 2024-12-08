package com.imd.projeto2.DTO;

import jakarta.validation.constraints.NotNull;

public record ClientePedidoDTO(
        @NotNull
        Long id_cliente,

        @NotNull
        Long id_pedido
) {
}
