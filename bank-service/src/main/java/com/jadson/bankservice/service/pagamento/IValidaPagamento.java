package com.jadson.bankservice.service.pagamento;

public interface IValidaPagamento<T> {
    void executar(T json);
}
