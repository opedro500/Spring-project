package com.imd.projeto2.models;

import com.imd.projeto2.DTO.ClienteDTO;
import com.imd.projeto2.DTO.PedidoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pedido;
    private String codigo;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;

    @ManyToMany
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_fk"),
            inverseJoinColumns = @JoinColumn(name = "cliente_fk")
    )
    private List<ProdutoModel> produtos;

    public void setProduto(ProdutoModel product) {
        this.produtos.add(product);
    }

    public PedidoModel() {
        this.ativo = true;
    }

    public void carregarDTO(PedidoDTO pedidoDTO) {
        if (pedidoDTO.codigo() != null) {
            this.codigo = pedidoDTO.codigo();
        }
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
