package br.unipar.progwebTrabalho1bi.services;

import br.unipar.progwebTrabalho1bi.dao.PedidoDAO;
import br.unipar.progwebTrabalho1bi.models.*;
import br.unipar.progwebTrabalho1bi.sei.PedidoSEI;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "br.unipar.progwebTrabalho1bi.sei.PedidoSEI")
public class PedidoSIB implements PedidoSEI {

    private EntityManager em;

    public PedidoSIB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");
        em = emf.createEntityManager();
    }

    @Override
    public Pedido realizarPedido(Integer idCliente, List<Integer> idsSabores, Integer idBorda) {
        Cliente cliente = em.find(Cliente.class, idCliente);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        //lista para armazenar os sabores que o cliente escolheu
        List<SaborPizza> saboresEscolhidos = new ArrayList<>();
        double totalSabores = 0;

        for (Integer idSabor : idsSabores) {
            SaborPizza sabor = em.find(SaborPizza.class, idSabor);
            if (sabor != null) {
                saboresEscolhidos.add(sabor); // adiciona o sabor à lista
                totalSabores += sabor.getPrecoSabor(); // soma o preço
            }
        }

        // Borda
        BordaPizza borda = em.find(BordaPizza.class, idBorda);
        double valorBorda = (borda != null) ? borda.getPrecoBorda() : 0;

        // Soma total
        double valorTotal = totalSabores + valorBorda;

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setSabores(saboresEscolhidos); //aqui to salvando os sabores no pedido
        pedido.setBorda(borda); //salvando também a borda escolhida
        pedido.setValorTotal(valorTotal);
        pedido.setStatus(StatusPedido.RECEBIDO);
        pedido.setDataPedido(new Date());

        em.getTransaction().begin();
        em.persist(pedido); //o JPA vai salvar também os relacionamentos
        em.getTransaction().commit();

        return pedido;
    }


    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        System.out.println("Buscando pedido ID: " + id);

        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = dao.buscarPorIdSemCache(id);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
        } else {
            System.out.println("Status atual do pedido: " + pedido.getStatus());
        }

        return pedido;
    }



    @Override
    public List<Pedido> listarPedidos() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    @Override
    public String consultarStatus(Integer idPedido) {
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = dao.buscarPorIdSemCache(idPedido); //aqui busca direto do banco

        return (pedido != null) ? pedido.getStatus().name() : "Pedido não encontrado";
    }


    @Override
    public Pedido atualizarStatusPedido(Integer idPedido) {
        Pedido pedido = em.find(Pedido.class, idPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }

        StatusPedido atual = pedido.getStatus();
        StatusPedido proximo;

        switch (atual) {
            case RECEBIDO -> proximo = StatusPedido.EM_PREPARO;
            case EM_PREPARO -> proximo = StatusPedido.PRONTO_PARA_RETIRADA;
            case PRONTO_PARA_RETIRADA -> proximo = StatusPedido.SAIU_PARA_ENTREGA;
            case SAIU_PARA_ENTREGA -> proximo = StatusPedido.ENTREGUE;
            case ENTREGUE -> throw new IllegalArgumentException("Pedido já foi entregue.");
            default -> throw new IllegalStateException("Status inválido.");
        }

        pedido.setStatus(proximo);

        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();

        return pedido;
    }
}

