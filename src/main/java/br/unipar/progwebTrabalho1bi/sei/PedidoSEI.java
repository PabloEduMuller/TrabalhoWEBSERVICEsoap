package br.unipar.progwebTrabalho1bi.sei;

import br.unipar.progwebTrabalho1bi.models.Pedido;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PedidoSEI {

    @WebMethod
    Pedido realizarPedido(
            @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "idsSabores") List<Integer> idsSabores,
            @WebParam(name = "idBorda") Integer idBorda
    );
    // cliente faz um pedido novo

    @WebMethod
    Pedido buscarPedidoPorId(@WebParam(name = "id") Integer id);
    // consulta os detalhes de um pedido específico

    @WebMethod
    List<Pedido> listarPedidos();
    // lista todos os pedidos da pizzaria

    @WebMethod
    String consultarStatus(@WebParam(name = "idPedido") Integer idPedido);
    // retorna o status atual do pedido ex: Recebido

    @WebMethod
    Pedido atualizarStatusPedido(@WebParam(name = "idPedido") Integer idPedido);
    // avança o status do pedido para o próximo estágio


}
