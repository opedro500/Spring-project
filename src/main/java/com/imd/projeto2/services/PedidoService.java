package com.imd.projeto2.services;

import com.imd.projeto2.DTO.PedidoDTO;
import com.imd.projeto2.models.ClienteModel;
import com.imd.projeto2.models.PedidoModel;
import com.imd.projeto2.models.ProdutoModel;
import com.imd.projeto2.repositories.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository repository;

    @Autowired
    ClienteService clientService;

    @Autowired
    ProdutoService productService;

    public List<PedidoModel> getOrders() {
        return repository.findAll();
    }

    public PedidoModel getOrderById(Long id) {
        Optional<PedidoModel> order = repository.findById(id);

        if (order.isEmpty()) {
            return null;
        }

        return order.get();
    }

    public PedidoModel postOrder(PedidoDTO pedidoDTO) {
        PedidoModel pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDTO, pedidoModel, "id_cliente", "ids_produtos");

        if (pedidoDTO.id_cliente() != null) {
            Optional<ClienteModel> clienteOptional = clientService.repository().findById(pedidoDTO.id_cliente());

            if (clienteOptional.isPresent()) {
                ClienteModel clienteModel = clienteOptional.get();
                pedidoModel.setCliente(clienteModel);
            } else {
                return null;
            }
        }

        if (pedidoDTO.ids_produtos() != null && !pedidoDTO.ids_produtos().isEmpty()) {
            List<ProdutoModel> produtos = pedidoDTO.ids_produtos().stream()
                    .map(id -> productService.repository().findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();

            if (produtos.isEmpty()) {
                return null;
            }

            pedidoModel.setProdutos(produtos);
        }

        return repository.save(pedidoModel);
    }

    public PedidoModel patchOrder(Long id_pedido, PedidoDTO pedidoDTO) {
        Optional<PedidoModel> orderOptional = repository.findById(id_pedido);
        if (orderOptional.isEmpty()) {
            return null;
        }

        PedidoModel pedidoModel = orderOptional.get();

        ClienteModel cliente = null;
        if (pedidoDTO.id_cliente() != null) {
            Optional<ClienteModel> clienteOptional = clientService.repository().findById(pedidoDTO.id_cliente());

            if (clienteOptional.isEmpty()) {
                return null;
            }

            cliente = clienteOptional.get();
        }

        pedidoModel.carregarDTO(pedidoDTO, cliente);

        return repository.save(pedidoModel);
    }

    public PedidoModel patchProductsInOrder(List<Long> id_product, Long id_order) {
        Optional<PedidoModel> orderOptional = repository.findById(id_order);

        if (orderOptional.isEmpty()) {
            return null;
        }

        PedidoModel order = orderOptional.get();
        order.limparProdutos();

        for (Long id : id_product) {
            Optional<ProdutoModel> productOptional = productService.repository().findById(id);

            if (productOptional.isPresent()) {
                ProdutoModel product = productOptional.get();
                order.setProduto(product);
            } else {
                return null;
            }
        }

        return repository.save(order);
    }


    public PedidoModel insertProductToOrder(Long id_product, Long id_order) {
        Optional<ProdutoModel> productOptional = productService.repository().findById(id_product);
        Optional<PedidoModel> orderOptional = repository.findById(id_order);

        if (productOptional.isEmpty() || orderOptional.isEmpty()) {
            return null;
        }

        PedidoModel order = orderOptional.get();
        ProdutoModel product = productOptional.get();

        order.setProduto(product);

        return repository.save(order);
    }

    public PedidoModel removeProductFromOrder(Long id_product, Long id_order) {
        Optional<ProdutoModel> productOptional = productService.repository().findById(id_product);
        Optional<PedidoModel> orderOptional = repository.findById(id_order);

        if (productOptional.isEmpty() || orderOptional.isEmpty()) {
            return null;
        }

        PedidoModel order = orderOptional.get();
        ProdutoModel product = productOptional.get();

        order.removeProduto(product);

        return repository.save(order);
    }


    public boolean disableOrder(Long id) {
        Optional<PedidoModel> order = repository.findById(id);

        if (order.isEmpty()) {
            return false;
        }

        PedidoModel pedidoModel = order.get();
        pedidoModel.desativar();
        repository.save(pedidoModel);

        return true;
    }

    public boolean enableOrder(Long id) {
        Optional<PedidoModel> order = repository.findById(id);

        if (order.isEmpty()) {
            return false;
        }

        PedidoModel pedidoModel = order.get();
        pedidoModel.ativar();
        repository.save(pedidoModel);

        return true;
    }

    public boolean deleteOrder(Long id) {
        Optional<PedidoModel> order = repository.findById(id);

        if (order.isEmpty()) {
            return false;
        }

        repository.delete(order.get());

        return true;
    }
}
