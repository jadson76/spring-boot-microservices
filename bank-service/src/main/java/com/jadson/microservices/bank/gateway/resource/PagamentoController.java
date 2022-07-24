package com.jadson.microservices.bank.gateway.resource;

import com.jadson.microservices.bank.gateway.json.PagamentoJson;
import com.jadson.microservices.bank.gateway.json.RetornoJson;
import com.jadson.microservices.bank.service.pagamento.IPagamentoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class PagamentoController {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoController.class);
    private final IPagamentoService pagamentoService;

    @RequestMapping(path = "/pagamento", method = RequestMethod.POST)
    public ResponseEntity<RetornoJson> pagamento(
            @Valid @NotNull @RequestBody PagamentoJson pagamentoJson) {
        logger.debug("Recebe requisição de pagamento.");
        pagamentoService.pagamento(pagamentoJson);

        RetornoJson retorno = new RetornoJson();
        retorno.setMensagem("Pagamento registrado com sucesso");

        return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
    }
}
