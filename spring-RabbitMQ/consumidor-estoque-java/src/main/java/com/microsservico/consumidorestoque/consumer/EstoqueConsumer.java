package com.microsservico.consumidorestoque.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservico.estoquepreco.constantes.RabbitmqConstantes;
import com.microservico.estoquepreco.dto.EstoqueDto;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

  @RabbitListener(queues = RabbitmqConstantes.FILA_ESTOQUE)
  private void consumidor(String mensagem) throws JsonMappingException, JsonProcessingException{
    EstoqueDto estoqueDto = new ObjectMapper().readValue(mensagem, EstoqueDto.class);

    System.out.println(estoqueDto.codigoproduto);
    System.out.println(estoqueDto.quantidade);
    System.out.println("------------------------------------");

  }
}
