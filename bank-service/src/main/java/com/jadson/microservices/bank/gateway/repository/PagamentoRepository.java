package com.jadson.microservices.bank.gateway.repository;

import com.jadson.microservices.bank.domain.Pagamento;
import org.springframework.data.repository.CrudRepository;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long>{
}
