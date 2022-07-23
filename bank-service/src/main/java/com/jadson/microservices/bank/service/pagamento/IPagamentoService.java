package com.jadson.microservices.bank.service.pagamento;

import com.jadson.microservices.bank.gateway.json.PagamentoJson;

public interface IPagamentoService {
    void pagamento(PagamentoJson pagamentoJson);
}
