package com.imd.projeto1.controllers;

import com.imd.projeto1.DTO.ClienteDTO;
import com.imd.projeto1.model.ClienteEntity;
import com.imd.projeto1.repositories.ClienteRepository;
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
    ClienteRepository clienteRepository;

    @GetMapping("/resgatarTodos")
    public ResponseEntity<Object> resgatarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/resgatar/{id}")
    public ResponseEntity<Object> resgatarClientePorId(@PathVariable(value = "id") Long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, clienteEntity);

        clienteRepository.save(clienteEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        ClienteEntity clienteEntity = cliente.get();
        clienteEntity.atualizarAtributosNaoNulos(clienteDTO);

        clienteRepository.save(clienteEntity);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirCliente(@PathVariable(value = "id") Long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        clienteRepository.delete(cliente.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
    }
}
