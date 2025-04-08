package br.unipar.progwebTrabalho1bi.services;

import br.unipar.progwebTrabalho1bi.dao.PedidoDAO;
import br.unipar.progwebTrabalho1bi.models.Pedido;
import br.unipar.progwebTrabalho1bi.models.StatusPedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Classe responsável por atualizar automaticamente os pedidos
public class AtualizadorDePedidosAgendado {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");

    public void iniciarAgendador() {
        scheduler.scheduleAtFixedRate(() -> {
            EntityManager em = emf.createEntityManager();

            try {
                em.getTransaction().begin();

                PedidoDAO pedidoDAO = new PedidoDAO();

                //aqui busca todos os pedidos que ainda não foram entregues
                List<Pedido> pedidos = pedidoDAO.buscarPedidosNaoEntregues(em);

                for (Pedido pedido : pedidos) {
                    StatusPedido proximoStatus = obterProximoStatus(pedido.getStatus());

                    if (proximoStatus != null) {
                        pedido.setStatus(proximoStatus);
                        pedidoDAO.atualizarPedido(em, pedido);
                        System.out.println("Pedido ID " + pedido.getId() + " atualizado para " + proximoStatus);
                    }
                }

                em.getTransaction().commit();

            } catch (Exception e) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }

        }, 0, 1, TimeUnit.MINUTES); // executa a cada 1 minuto
    }



    //aqui é lógica de transição de status
    private StatusPedido obterProximoStatus(StatusPedido statusAtual) {
        switch (statusAtual) {
            case RECEBIDO:
                return StatusPedido.EM_PREPARO;
            case EM_PREPARO:
                return StatusPedido.PRONTO_PARA_RETIRADA;
            case PRONTO_PARA_RETIRADA:
                return StatusPedido.SAIU_PARA_ENTREGA;
            case SAIU_PARA_ENTREGA:
                return StatusPedido.ENTREGUE;
            default:
                return null;
        }
    }
}
