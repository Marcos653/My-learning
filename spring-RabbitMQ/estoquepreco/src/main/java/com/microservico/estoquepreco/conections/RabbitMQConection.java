package com.microservico.estoquepreco.conections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.microservico.estoquepreco.constantes.RabbitmqConstantes;

@Component
public class RabbitMQConection {
    private static final String NOME_ENCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_ENCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE,
        troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitmqConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitmqConstantes.FILA_PRECO);

        DirectExchange troce = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troce);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troce);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troce);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);

    }

}
