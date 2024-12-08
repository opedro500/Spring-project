package com.imd.projeto2.services;

import com.imd.projeto2.DTO.ClienteDTO;
import com.imd.projeto2.models.ClienteModel;
import com.imd.projeto2.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public List<ClienteModel> getClients() {
        return repository.findAll();
    }

    public List<ClienteModel> getClientsAtivoTrue() {
        return repository.findAllByAtivoTrue();
    }

    public ClienteModel getClientById(Long id) {
        Optional<ClienteModel> client = repository.findById(id);

        if (client.isEmpty()) {
            return null;
        }

        return client.get();
    }

    public ClienteModel postClient(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDTO, clienteModel);

        return repository.save(clienteModel);
    }

    public ClienteModel putClient(Long id, ClienteDTO clienteDTO) {
        Optional<ClienteModel> client = repository.findById(id);

        if (client.isEmpty()) {
            return null;
        }

        ClienteModel clienteModel = client.get();
        clienteModel.carregarDTO(clienteDTO);

        return repository.save(clienteModel);
    }

    public boolean disableClient(Long id) {
        Optional<ClienteModel> client = repository.findById(id);

        if (client.isEmpty()) {
            return false;
        }

        ClienteModel clienteModel = client.get();
        clienteModel.desativar();
        repository.save(clienteModel);

        return true;
    }

    public boolean enableClient(Long id) {
        Optional<ClienteModel> client = repository.findById(id);

        if (client.isEmpty()) {
            return false;
        }

        ClienteModel clienteModel = client.get();
        clienteModel.ativar();
        repository.save(clienteModel);

        return true;
    }

    public boolean deleteClient(Long id) {
        Optional<ClienteModel> client = repository.findById(id);

        if (client.isEmpty()) {
            return false;
        }

        repository.delete(client.get());

        return true;
    }

    public ClienteRepository repository() {
        return repository;
    }
}
