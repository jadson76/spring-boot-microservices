package com.jadson.microservices.bank.service.cartao;


import com.jadson.microservices.bank.domain.Cartao;
import com.jadson.microservices.bank.gateway.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class CartaoService implements ICartaoService{
    private CartaoRepository cartaoRepository;

    @Autowired
    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Override
    public boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao) {
        return cartaoRepository.findCartaoValido(codigoSegurancaCartao, nroCartao) > 0;
    }

    @Override
    public boolean isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra) {
        return cartaoRepository.isSaldoSuficiente(codigoSegurancaCartao, nroCartao, valorCompra) > 0;
    }

    @Override
    public Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao) {
        return cartaoRepository.findCartao(codigoSegurancaCartao, nroCartao);
    }

    @Override
    @Transactional
    public void atualizarSaldo(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra) {
        cartaoRepository.atualizarSaldo(codigoSegurancaCartao, nroCartao, valorCompra);
    }
}
