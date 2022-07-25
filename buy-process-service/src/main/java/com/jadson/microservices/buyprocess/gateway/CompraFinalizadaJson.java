package com.jadson.microservices.buyprocess.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraFinalizadaJson {
    private CompraChaveJson compraChaveJson;
    private String mensagem;
    private boolean pagamentoOK;
}
