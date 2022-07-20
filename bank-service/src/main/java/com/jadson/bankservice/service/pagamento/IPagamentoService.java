package com.jadson.bankservice.service.pagamento;

import com.jadson.bankservice.gateway.json.PagamentoJson;

public interface IPagamentoService {
    void pagamento(PagamentoJson pagamentoJson);
}
