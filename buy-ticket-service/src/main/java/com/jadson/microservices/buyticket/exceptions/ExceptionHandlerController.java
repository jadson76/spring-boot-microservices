package com.jadson.microservices.buyticket.exceptions;

import com.jadson.microservices.buyticket.gateway.json.RetornoJson;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String MSG_ERRO_AMQP = "Erro ao submeter para fila - %s";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String process(MethodArgumentNotValidException ex) {

        StringBuilder sb = new StringBuilder();

        for( ObjectError item : ex.getBindingResult().getAllErrors() ){
            sb.append(item.getDefaultMessage());
            sb.append("\n");
        }

        return sb.toString();
    }

    @ExceptionHandler(AmqpRejectAndDontRequeueException.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RetornoJson process(AmqpRejectAndDontRequeueException ex) {
        return new RetornoJson(String.format(MSG_ERRO_AMQP,ex.getMessage()));
    }

    @ExceptionHandler(JsonMapException.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RetornoJson process(JsonMapException ex) {
        return new RetornoJson(ex.getMessage());
    }
}
