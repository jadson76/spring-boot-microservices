package com.jadson.microservices.buyticket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jadson.microservices.buyticket.exceptions.JsonMapException;
import com.jadson.microservices.buyticket.gateway.json.CompraChaveJson;
import com.jadson.microservices.buyticket.gateway.json.CompraJson;
import com.jadson.microservices.buyticket.gateway.json.RetornoJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompraServiceImpl implements CompraService{

    private static final String MSG_SUCESSO = "Compra registrada com sucesso. Aguarda a confirmação do pagamento.";
    private static final String MSG_ERRO_JSON = "Erro ao converter JSON ";
    @Autowired
    private ProducerService producerService;

    @Override
    public RetornoJson executa(CompraJson compraJson) {
        CompraChaveJson compraChaveJson = new CompraChaveJson();
        compraChaveJson.setCompraJson(compraJson);
        compraChaveJson.setChave(UUID.randomUUID().toString());

        String mensagem =  jsonMapper(compraChaveJson);

        producerService.enviar(mensagem);
        return criaRetorno(compraChaveJson.getChave());
    }

    private String jsonMapper(CompraChaveJson compraChaveJson) {
        ObjectMapper obj = new ObjectMapper();
        try{
           return  obj.writeValueAsString(compraChaveJson);
        }catch (JsonProcessingException ex) {
            throw new JsonMapException(MSG_ERRO_JSON,ex);
        }
    }

    private RetornoJson criaRetorno(String chave) {
        RetornoJson retorno = new RetornoJson();
        retorno.setMensagem(MSG_SUCESSO);
        retorno.setChavePesquisa(chave);
        return retorno;
    }
}
