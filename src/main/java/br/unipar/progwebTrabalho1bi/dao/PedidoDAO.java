package br.unipar.progwebTrabalho1bi.dao;

import br.unipar.progwebTrabalho1bi.models.Pedido;
import br.unipar.progwebTrabalho1bi.models.StatusPedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PedidoDAO {

    public List<Pedido> buscarPedidosPorStatus(EntityManager em, StatusPedido status) {
        TypedQuery<Pedido> query = em.createQuery(
                "SELECT p FROM Pedido p WHERE p.status = :status", Pedido.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
    public List<Pedido> buscarPedidosNaoEntregues(EntityManager em) {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.status != :statusFinal", Pedido.class)
                .setParameter("statusFinal", StatusPedido.ENTREGUE)
                .getResultList();
    }


    public void atualizarPedido(EntityManager em, Pedido pedido) {
        em.merge(pedido);
        em.flush();
    }

    public Pedido buscarPorIdSemCache(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");
        EntityManager em = emf.createEntityManager();

        Pedido pedido = null;

        try {
            pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                em.refresh(pedido); // força pegar do banco, não cache
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        return pedido;
    }



}
