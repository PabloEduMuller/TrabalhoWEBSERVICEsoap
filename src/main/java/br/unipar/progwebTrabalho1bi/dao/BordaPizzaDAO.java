package br.unipar.progwebTrabalho1bi.dao;

import br.unipar.progwebTrabalho1bi.models.BordaPizza;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BordaPizzaDAO {

    private EntityManager em;

    public BordaPizzaDAO(EntityManager em) {
        this.em = em;
    }


    public void salvarBordaPizza(BordaPizza borda) {
        em.getTransaction().begin();
        em.persist(borda);
        em.getTransaction().commit();
    }

    public BordaPizza buscarPorId(Integer id) {
        return em.find(BordaPizza.class, id);
    }

    public List<BordaPizza> listarTodasBordas() {
        return em.createQuery("SELECT b FROM BordaPizza b", BordaPizza.class).getResultList();
    }

    public void removerBorda(BordaPizza borda) {
        em.getTransaction().begin();
        if (!em.contains(borda)) {
            borda = em.merge(borda);
        }
        em.remove(borda);
        em.getTransaction().commit();
    }
}
