package br.unipar.progwebTrabalho1bi.services;

import br.unipar.progwebTrabalho1bi.dao.ClienteDAO;
import br.unipar.progwebTrabalho1bi.models.Cliente;
import br.unipar.progwebTrabalho1bi.sei.ClienteSEI;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.List;

@WebService(endpointInterface = "br.unipar.progwebTrabalho1bi.sei.ClienteSEI")
public class ClienteSIB implements ClienteSEI {

    private ClienteDAO clienteDAO = new ClienteDAO();

    private EntityManager em;

    public ClienteSIB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");

        em = emf.createEntityManager();
    }


    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}
