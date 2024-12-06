package com.imd.projeto2.controllers;

import com.imd.projeto2.DTO.*;
import com.imd.projeto2.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    PedidoService service;

    @GetMapping("/resgatar")
    public ResponseEntity<Object> resgatarPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOrders());
    }

    @GetMapping("/resgatar/{id}")
    public ResponseEntity<Object> resgatarPedidoPorId(@PathVariable(value = "id") Long id) {
        var order = service.getOrderById(id);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postOrder(pedidoDTO));
    }

    @PatchMapping("/atualizar-cliente")
    public ResponseEntity<Object> atualizarClienteEmPedido(@RequestBody @Valid ClientePedidoDTO clientePedidoDTO) {
        var order = service.patchClientInOrder(clientePedidoDTO.id_cliente(), clientePedidoDTO.id_pedido());

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente ou pedido não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cliente inserido no pedido com sucesso.");
    }

    @PatchMapping("/atualizar-produto")
    public ResponseEntity<Object> atualizarProdutoEmPedido(@RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        var order = service.patchProductInOrder(produtoPedidoDTO.id_produto(), produtoPedidoDTO.id_pedido());

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto ou pedido não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produto inserido no pedido com sucesso.");
    }

    @PatchMapping("/atualizar-produtos")
    public ResponseEntity<Object> atualizarProdutosEmPedido(@RequestBody @Valid ProdutosPedidoDTO produtosPedidoDTO) {
        var order = service.patchProductsInOrder(produtosPedidoDTO.ids_produtos(), produtosPedidoDTO.id_pedido());

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado ou algum produto não é válido.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produtos inseridos no pedido com sucesso.");
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Object> desativarPedido(@PathVariable(value = "id") Long id) {
        var order = service.disableOrder(id);

        if (!order) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Pedido desativado com sucesso.");
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Object> ativarPedido(@PathVariable(value = "id") Long id) {
        var order = service.enableOrder(id);

        if (!order) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Pedido ativado com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirPedido(@PathVariable(value = "id") Long id) {
        var order = service.deleteOrder(id);

        if (!order) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Pedido excluído com sucesso.");
    }
}
