package com.jadson.bankservice.service.cartao;

import com.jadson.bankservice.domain.Cartao;

import java.math.BigDecimal;

public interface ICartaoService {
    boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao);

    boolean isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra);

    Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao);

    void atualizarSaldo(Integer codigoSegurancaCartao,Integer nroCartao, BigDecimal valorCompra);
}
