package com.imd.projeto2.controllers;

import com.imd.projeto2.DTO.ClienteDTO;
import com.imd.projeto2.models.ClienteModel;
import com.imd.projeto2.repositories.ClienteRepository;
import com.imd.projeto2.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService service;

    @GetMapping("/resgatar")
    public ResponseEntity<Object> resgatarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getClients());
    }

    @GetMapping("/resgatar-ativos")
    public ResponseEntity<Object> resgatarClientesAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getClientsAtivoTrue());
    }

    @GetMapping("/resgatar/{id}")
    public ResponseEntity<Object> resgatarClientePorId(@PathVariable(value = "id") Long id) {
        var client = service.getClientById(id);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postClient(clienteDTO));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") Long id, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.patchClient(id, clienteDTO));
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Object> desativarCliente(@PathVariable(value = "id") Long id) {
        var client = service.disableClient(id);

        if (!client) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cliente desativado com sucesso.");
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<Object> ativarCliente(@PathVariable(value = "id") Long id) {
        var client = service.enableClient(id);

        if (!client) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cliente ativado com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirCliente(@PathVariable(value = "id") Long id) {
        var client = service.deleteClient(id);

        if (!client) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
    }
}
