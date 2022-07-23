package com.jadson.microservices.bank.service.pagamento;

public interface IValidaPagamento<T> {
    void executar(T json);
}
