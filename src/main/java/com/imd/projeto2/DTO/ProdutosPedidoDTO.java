package com.imd.projeto2.DTO;

import java.util.List;

public record ProdutosPedidoDTO(
        List<Long> ids_produtos,
        Long id_pedido
) {
}
