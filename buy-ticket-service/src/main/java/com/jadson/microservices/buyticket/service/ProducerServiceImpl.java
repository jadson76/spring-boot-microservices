package com.jadson.microservices.buyticket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${fila.saida}")
    private String nomeFila;

    @Override
    public void enviar(String mensagem) {
        logger.debug("Processa envio compra para fila.");
        try {
            rabbitTemplate.convertAndSend(nomeFila, mensagem);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
