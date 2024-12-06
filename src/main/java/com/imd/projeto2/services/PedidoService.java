package com.imd.projeto2.services;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.imd.projeto2.DTO.ClienteDTO;
import com.imd.projeto2.DTO.PedidoDTO;
import com.imd.projeto2.models.ClienteModel;
import com.imd.projeto2.models.PedidoModel;
import com.imd.projeto2.models.ProdutoModel;
import com.imd.projeto2.repositories.ClienteRepository;
import com.imd.projeto2.repositories.PedidoRepository;
import com.imd.projeto2.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        BeanUtils.copyProperties(pedidoDTO, pedidoModel);

        return repository.save(pedidoModel);
    }

    public PedidoModel patchClientInOrder(Long id_client, Long id_order) {
        Optional<ClienteModel> clientOpitional = clientService.repository().findById(id_client);
        Optional<PedidoModel> orderOpitional = repository.findById(id_order);

        if(clientOpitional.isEmpty() || orderOpitional.isEmpty()){
            return null;
        }

        ClienteModel client = clientOpitional.get();
        PedidoModel order = orderOpitional.get();

        order.setCliente(client);

        return repository.save(order);
    }

    public PedidoModel patchProductInOrder(Long id_product, Long id_order) {
        Optional<ProdutoModel> productOpitional = productService.repository().findById(id_product);
        Optional<PedidoModel> orderOpitional = repository.findById(id_order);

        if(productOpitional.isEmpty() || orderOpitional.isEmpty()){
            return null;
        }

        ProdutoModel product = productOpitional.get();
        PedidoModel order = orderOpitional.get();

        order.setProduto(product);

        return repository.save(order);
    }

    public PedidoModel patchProductsInOrder(List<Long> id_product, Long id_order) {
        Optional<PedidoModel> orderOpitional = repository.findById(id_order);

        if(orderOpitional.isEmpty()){
            return null;
        }

        PedidoModel order = orderOpitional.get();

        for(Long id : id_product){
            ProdutoModel product = productService.repository().findById(id).get();

            if(product != null){
                order.setProduto(product);
            }
        }

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
