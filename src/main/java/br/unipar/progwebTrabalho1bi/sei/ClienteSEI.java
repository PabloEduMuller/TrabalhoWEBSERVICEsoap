package br.unipar.progwebTrabalho1bi.sei;

import br.unipar.progwebTrabalho1bi.models.Cliente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ClienteSEI {

    @WebMethod
    Cliente cadastrarCliente(@WebParam(name = "cliente") Cliente cliente);

    @WebMethod
    Cliente buscarClientePorId(@WebParam(name = "id") Integer id);

    @WebMethod
    List<Cliente> listarTodosClientes();
}
