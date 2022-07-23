package com.jadson.microservices.buyticket.service;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${fila.saida}")
    private String nomeFila;

    @Override
    public void enviar(String mensagem) {
        try {
            rabbitTemplate.convertAndSend(nomeFila, mensagem);
        }catch (Exception ex){
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
