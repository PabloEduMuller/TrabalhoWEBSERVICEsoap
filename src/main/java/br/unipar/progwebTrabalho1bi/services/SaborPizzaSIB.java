package br.unipar.progwebTrabalho1bi.services;

import br.unipar.progwebTrabalho1bi.dao.SaborPizzaDAO;
import br.unipar.progwebTrabalho1bi.models.SaborPizza;
import br.unipar.progwebTrabalho1bi.sei.SaborPizzaSEI;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@WebService(endpointInterface = "br.unipar.progwebTrabalho1bi.sei.SaborPizzaSEI")
public class SaborPizzaSIB implements SaborPizzaSEI {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");
    private EntityManager em = emf.createEntityManager();
    private SaborPizzaDAO dao = new SaborPizzaDAO(em);

    @Override
    public void adicionaSaborPizza(String nomeSabor, double precoSabor) {
        SaborPizza sabor = new SaborPizza();
        sabor.setNomeSabor(nomeSabor);
        sabor.setPrecoSabor(precoSabor);
        dao.salvarSaborPizza(sabor);
    }

    @Override
    public List<SaborPizza> listaSaboresPizza() {
        return dao.listarTodosSabores();
    }

    @Override
    public void removerSaborPizza(Integer idSabor) {
        SaborPizza sabor = dao.buscarPorId(idSabor);
        if (sabor != null) {
            dao.removerSabores(sabor);
        }
    }
}
