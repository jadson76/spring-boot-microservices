package com.jadson.microservices.buyprocess.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraJson {
    private Integer codigoPassagem;

    private Integer nroCartao;

    private Integer codigoSegurancaCartao;

    private BigDecimal valorPassagem;
}
