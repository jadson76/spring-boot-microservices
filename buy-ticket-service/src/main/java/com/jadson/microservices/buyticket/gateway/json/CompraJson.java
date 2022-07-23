package com.jadson.microservices.buyticket.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraJson {

    @NotNull(message="Código da passagem é obrigatório")
    private Integer codigoPassagem;

    @NotNull(message="Número do cartão é obrigatório")
    private Integer nroCartao;

    @NotNull(message="Código de segurança do cartão é obrigatório")
    private Integer codigoSegurancaCartao;

    @NotNull(message="Valor da compra é obrigatório")
    private BigDecimal valorPassagem;
}
