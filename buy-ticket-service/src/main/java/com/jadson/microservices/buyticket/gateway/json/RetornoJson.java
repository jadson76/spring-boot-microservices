package com.jadson.microservices.buyticket.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoJson {
    private String mensagem;
    private String chavePesquisa;

    public RetornoJson(String message) {
        mensagem = message;
    }

    public void setChavePesquisa(String chavePesquisa) {
        this.chavePesquisa = chavePesquisa;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
