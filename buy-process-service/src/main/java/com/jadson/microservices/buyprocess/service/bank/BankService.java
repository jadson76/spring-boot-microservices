package com.jadson.microservices.buyprocess.service.bank;

import com.jadson.microservices.buyprocess.gateway.CompraChaveJson;
import com.jadson.microservices.buyprocess.vo.PagamentoRetorno;

import java.io.IOException;

public interface BankService {
    PagamentoRetorno pagar(CompraChaveJson compraChaveJson) throws IOException;
}
