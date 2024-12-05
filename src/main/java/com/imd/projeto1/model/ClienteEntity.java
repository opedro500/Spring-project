package com.imd.projeto1.model;

import com.imd.projeto1.DTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;
    private String nome;
    private String cpf;
    private Genero genero;
    private LocalDate data_nascimento;

    public void atualizarAtributosNaoNulos(ClienteDTO clienteDTO) {
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
}
