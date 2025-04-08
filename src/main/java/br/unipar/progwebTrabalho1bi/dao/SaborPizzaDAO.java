package br.unipar.progwebTrabalho1bi.dao;

import br.unipar.progwebTrabalho1bi.models.SaborPizza;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SaborPizzaDAO {

    private EntityManager em;

    public SaborPizzaDAO(EntityManager em) {
        this.em = em;

    }

    //estou salvando o sabor
    public void salvarSaborPizza(SaborPizza saborPizza) {
        em.getTransaction().begin();
        em.persist(saborPizza);
        em.getTransaction().commit();
    }

    public SaborPizza buscarPorId(Integer id){
        return em.find(SaborPizza.class, id);// ta buscando pelo id do sabor
    }

    //serve pra listar os sabores cadastrados
    public List<SaborPizza> listarTodosSabores(){
        return em.createQuery("SELECT s FROM SaborPizza s", SaborPizza.class).getResultList();
    }

    //serve pra remover um sabor
    public void removerSabores(SaborPizza saborPizza){
        em.getTransaction().begin();
        em.remove(em.contains(saborPizza) ? saborPizza : em.merge(saborPizza));
        em.getTransaction().commit();
    }



}
