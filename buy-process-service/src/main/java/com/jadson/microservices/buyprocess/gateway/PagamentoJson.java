package com.jadson.microservices.buyprocess.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoJson {
    private Integer nroCartao;
    private Integer codigoSegurancaCartao;
    private BigDecimal valorCompra;
}
