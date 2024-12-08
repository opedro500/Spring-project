package com.imd.projeto2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imd.projeto2.DTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;
    private String nome;
    private String cpf;
    private Genero genero;
    private LocalDate data_nascimento;
    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<PedidoModel> pedidos;

    public ClienteModel() {
        this.ativo = true;
    }

    public void carregarDTO(ClienteDTO clienteDTO) {
        if (clienteDTO.nome() != null) {
            this.nome = clienteDTO.nome();
        }
        if (clienteDTO.cpf() != null) {
            this.cpf = clienteDTO.cpf();
        }
        if (clienteDTO.genero() != null) {
            this.genero = clienteDTO.genero();
        }
        if (clienteDTO.data_nascimento() != null) {
            this.data_nascimento = clienteDTO.data_nascimento();
        }
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
