package br.unipar.progwebTrabalho1bi.services;

import br.unipar.progwebTrabalho1bi.dao.BordaPizzaDAO;
import br.unipar.progwebTrabalho1bi.models.BordaPizza;
import br.unipar.progwebTrabalho1bi.sei.BordaPizzaSEI;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@WebService(endpointInterface = "br.unipar.progwebTrabalho1bi.sei.BordaPizzaSEI")
public class BordaPizzaSIB implements BordaPizzaSEI {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzariaPU");
    private EntityManager em = emf.createEntityManager();
    private BordaPizzaDAO dao = new BordaPizzaDAO(em);

    @Override
    public void adicionaBordaPizza(String nomeBorda, double precoBorda) {
        BordaPizza borda = new BordaPizza();
        borda.setNomeBorda(nomeBorda);
        borda.setPrecoBorda(precoBorda);
        dao.salvarBordaPizza(borda);
    }

    @Override
    public List<BordaPizza> listaBordasPizza() {
        return dao.listarTodasBordas();
    }

    @Override
    public void removerBordaPizza(Integer idBorda) {
        BordaPizza borda = dao.buscarPorId(idBorda);
        if (borda != null) {
            dao.removerBorda(borda);
        }
    }
}
