package com.jadson.bankservice.service.pagamento;

import com.jadson.bankservice.domain.Pagamento;
import com.jadson.bankservice.gateway.json.PagamentoJson;
import com.jadson.bankservice.gateway.repository.PagamentoRepository;
import com.jadson.bankservice.service.cartao.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PagamentoService implements IPagamentoService{

    private PagamentoRepository pagamentoRepository;
    private ICartaoService cartaoService;
    private IValidaPagamento<PagamentoJson> validaPagamento;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository, ICartaoService cartaoService,
                            IValidaPagamento<PagamentoJson> validaPagamento) {
        this.cartaoService = cartaoService;
        this.pagamentoRepository = pagamentoRepository;
        this.validaPagamento = validaPagamento;
    }

    @Override
    @Transactional
    public void pagamento(PagamentoJson pagamentoJson) {

        validaPagamento.executar(pagamentoJson);
        Pagamento pagamento = new Pagamento();
        pagamento.setValorCompra(pagamentoJson.getValorCompra());
        pagamento.setCartao(cartaoService.getCartao(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()));

        pagamentoRepository.save(pagamento);

        cartaoService.atualizarSaldo(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra());
    }
}
