package com.jadson.microservices.buyticket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jadson.microservices.buyticket.gateway.json.CompraJson;
import com.jadson.microservices.buyticket.gateway.json.RetornoJson;

public interface CompraService {
    RetornoJson executa(CompraJson compraJson) ;

}
