package br.unipar.progwebTrabalho1bi.dao;

import br.unipar.progwebTrabalho1bi.models.Cliente;
import br.unipar.progwebTrabalho1bi.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO {

    private EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager();
    }

    //LISTAR***
    //aqui vai buscar todos os clientes do banco de dados
    public List<Cliente> findAll(){
        EntityManager em = getEntityManager();

        List<Cliente> lista = null;

        try {
            // consulta JPQL (tipo um SQL) pra buscar todos os clientes da tabela
            lista = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            // é tipo SELECT * FROM cliente;
        } finally {
            // fecha pra não travar nada por uso de memoria
            em.close();
        }
        //vai mostrar a lista de cliientes
        return lista;
    }

    //SALVAR******
    public Cliente salvar(Cliente cliente) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            if (cliente.getId() == 0) { //serve pra saber se não foi salvo no banco ainda
                em.persist(cliente);
            } else {
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // desfaz se der erro
            throw e;
        } finally {
            em.close();
        }

        return cliente;
    }

    //PROCURA PELO ID*****
    public Cliente findById(int id) {
        EntityManager em = getEntityManager();
        Cliente cliente = null;

        try {
            cliente = em.find(Cliente.class, id); // procura pelo ID
        } finally {
            em.close();
        }

        return cliente;
    }

    public void deletar(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);

            if (cliente != null) {//aqui ta falando que se for  "diferente de" nulo, vai fazer a remção
                em.remove(cliente); //remove se existir
            }
            em.getTransaction().commit(); //comita a remoçao
        } catch (Exception e) {
            em.getTransaction().rollback(); //desfaz se deu ruim
            throw e;
        } finally {
            em.close();
        }
    }



}
