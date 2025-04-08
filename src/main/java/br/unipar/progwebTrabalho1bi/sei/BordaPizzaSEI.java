package br.unipar.progwebTrabalho1bi.sei;

import br.unipar.progwebTrabalho1bi.models.BordaPizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface BordaPizzaSEI {

    @WebMethod
    void adicionaBordaPizza(
            @WebParam(name = "nomeBorda") String nomeBorda,
            @WebParam(name = "precoBorda") double precoBorda
    );

    @WebMethod
    List<BordaPizza> listaBordasPizza();

    @WebMethod
    void removerBordaPizza(@WebParam(name = "idBorda") Integer idBorda);
}
