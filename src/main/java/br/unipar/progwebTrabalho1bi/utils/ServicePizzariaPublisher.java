package br.unipar.progwebTrabalho1bi.utils;

import br.unipar.progwebTrabalho1bi.services.*;
import jakarta.xml.ws.Endpoint;

public class ServicePizzariaPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/trab5sem1bi/ClienteService", new ClienteSIB());
        Endpoint.publish("http://localhost:8080/trab5sem1bi/SaborPizzaService", new SaborPizzaSIB());
        Endpoint.publish("http://localhost:8080/trab5sem1bi/BordaPizzaService", new BordaPizzaSIB());
        Endpoint.publish("http://localhost:8080/trab5sem1bi/PedidoService", new PedidoSIB());
        System.out.println("SERVIÇO POSTADO COM SUCESSO!");
        new AtualizadorDePedidosAgendado().iniciarAgendador();

        System.out.println("Agendador iniciado!");;

    }
}
