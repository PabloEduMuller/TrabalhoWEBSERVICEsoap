package br.unipar.progwebTrabalho1bi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class BordaPizza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBorda;

    private String nomeBorda;
    private double precoBorda;

    public BordaPizza() {}

    public BordaPizza(String nomeBorda, double precoBorda) {
        this.nomeBorda = nomeBorda;
        this.precoBorda = precoBorda;
    }
}
