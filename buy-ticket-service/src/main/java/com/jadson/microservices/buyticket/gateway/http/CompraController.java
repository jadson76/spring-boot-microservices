package com.jadson.microservices.buyticket.gateway.http;

import com.jadson.microservices.buyticket.gateway.json.CompraJson;
import com.jadson.microservices.buyticket.gateway.json.RetornoJson;
import com.jadson.microservices.buyticket.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin("*")
@RestController(value = "/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RetornoJson> pagamento(
            @Valid @NotNull @RequestBody CompraJson compraJson)  {
        return new ResponseEntity<RetornoJson>(compraService.executa(compraJson), HttpStatus.OK);
    }
}
