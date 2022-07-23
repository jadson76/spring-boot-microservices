package com.jadson.microservices.buyticket.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraChaveJson {

    private String chave;
    private CompraJson compraJson;

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setCompraJson(CompraJson compraJson) {
        this.compraJson = compraJson;
    }

    public CompraJson getCompraJson() {
        return compraJson;
    }

    public String getChave() {
        return chave;
    }
}
