package com.jadson.microservices.bank.service.pagamento;

import com.jadson.microservices.bank.gateway.exceptions.PagamentoException;
import com.jadson.microservices.bank.gateway.json.PagamentoJson;
import com.jadson.microservices.bank.service.cartao.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaPagamento implements IValidaPagamento<PagamentoJson>{


    private static final String CARTAO_INVALIDO = "O Cartão número %s é inválido.";
    private static final String CARTAO_SEM_SALDO = "O Cartão número %s nao possui saldo suficiente.";
    private ICartaoService cartaoService;

    @Autowired
    public ValidaPagamento(ICartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @Override
    public void executar(PagamentoJson json) {
        if( !cartaoService.isValido(json.getCodigoSegurancaCartao(), json.getNroCartao()) ){
            throw new PagamentoException(String.format(CARTAO_INVALIDO,json.getNroCartao()));
        }

        if( !cartaoService.isSaldoSuficiente(json.getCodigoSegurancaCartao(),
                json.getNroCartao(), json.getValorCompra()) ){
            throw new PagamentoException(String.format(CARTAO_SEM_SALDO,json.getNroCartao()));
        }
    }
}
