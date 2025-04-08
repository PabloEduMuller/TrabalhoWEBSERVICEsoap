package br.unipar.progwebTrabalho1bi.models;

import br.unipar.progwebTrabalho1bi.utils.LocalDateAdapter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;

    @XmlTransient //JAXB vai ignorar esse campo(tive que pedir ajuda do chat porque estava dando erro)
    private LocalDate dataNascimento;

    // Getter com o adaptador JAXB
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cliente(String nome, String telefone, String cpf,
                   String endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public Cliente() {}
}
