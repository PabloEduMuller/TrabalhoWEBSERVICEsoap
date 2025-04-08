package br.unipar.progwebTrabalho1bi.sei;

import br.unipar.progwebTrabalho1bi.models.SaborPizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface SaborPizzaSEI {

    @WebMethod
    void adicionaSaborPizza(
            @WebParam(name = "nomeSabor") String nomeSabor,
            @WebParam(name = "precoSabor") double precoSabor
    );

    @WebMethod
    List<SaborPizza> listaSaboresPizza();

    @WebMethod
    void removerSaborPizza(@WebParam(name = "idSabor") Integer idSabor);
}
