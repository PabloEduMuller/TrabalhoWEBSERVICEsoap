package br.unipar.progwebTrabalho1bi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPizza;
    private String sabor;

    public Pizza(int idPizza, String sabor) {
        this.sabor = sabor;
    }

    public Pizza() {}

}

