package br.unipar.progwebTrabalho1bi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class SaborPizza implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSabor;

    private String nomeSabor;
    private double precoSabor;

    public SaborPizza() {}

    public SaborPizza(String nomeSabor, double precoSabor) {
        this.nomeSabor = nomeSabor;
        this.precoSabor = precoSabor;
    }
}
