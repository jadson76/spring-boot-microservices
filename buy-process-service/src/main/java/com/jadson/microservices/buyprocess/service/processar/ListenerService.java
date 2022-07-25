package com.jadson.microservices.buyprocess.service.processar;


import com.jadson.microservices.buyprocess.gateway.CompraChaveJson;
import com.jadson.microservices.buyprocess.gateway.CompraFinalizadaJson;
import com.jadson.microservices.buyprocess.service.bank.BankService;
import com.jadson.microservices.buyprocess.vo.PagamentoRetorno;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class ListenerService {
    @Autowired
    private BankService bank;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${fila.entrada}")
    private String nomeFilaRepublicar;

    @Value("${fila.finalizado}")
    private String nomeFilaFinalizado;

    @HystrixCommand(fallbackMethod = "republicOnMessage")
    @RabbitListener(queues="${fila.entrada}")
    public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException {

        String json = new String(message.getBody(), "UTF-8");

        System.out.println("Mensagem recebida:"+json);

        ObjectMapper mapper = new ObjectMapper();
        CompraChaveJson compraChaveJson = mapper.readValue(json, CompraChaveJson.class);

        PagamentoRetorno pg = bank.pagar(compraChaveJson);

        CompraFinalizadaJson compraFinalizadaJson = new CompraFinalizadaJson();
        compraFinalizadaJson.setCompraChaveJson(compraChaveJson);
        compraFinalizadaJson.setPagamentoOK(pg.isPagamentoOK());
        compraFinalizadaJson.setMensagem(pg.getMensagem());

        org.codehaus.jackson.map.ObjectMapper obj = new org.codehaus.jackson.map.ObjectMapper();
        String jsonFinalizado = obj.writeValueAsString(compraFinalizadaJson);

        rabbitTemplate.convertAndSend(nomeFilaFinalizado, jsonFinalizado);
    }

    public void republicOnMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
        System.out.println("Republicando mensagem...");
        rabbitTemplate.convertAndSend(nomeFilaRepublicar, message);
    }
}
