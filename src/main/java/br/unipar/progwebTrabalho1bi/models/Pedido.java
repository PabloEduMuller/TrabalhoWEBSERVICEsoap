package br.unipar.progwebTrabalho1bi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //associa o pedido ao cliente
    @ManyToOne
    private Cliente cliente;

    //lista de sabores escolhidos (muitos sabores para um pedido)
    @ManyToMany
    private List<SaborPizza> sabores;

    //apenas uma borda por pedido
    @ManyToOne
    private BordaPizza borda;

    private double valorTotal; //valor total será calculado com base na soma dos preços

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    //construtor padrão que já define a data e o status inicial
    public Pedido() {
        this.dataPedido = new Date();
        this.status = StatusPedido.RECEBIDO;
    }
}
