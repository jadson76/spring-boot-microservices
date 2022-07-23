package com.jadson.microservices.bank.gateway.json;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PagamentoJson {

	@NotNull(message="Número do cartão é obrigatório")
	private Integer nroCartao;
	
	@NotNull(message="Código de segurança do cartão é obrigatório")
	private Integer codigoSegurancaCartao;
	
	@NotNull(message="Valor da compra é obrigatório")
	private BigDecimal valorCompra;

	public Integer getNroCartao() {
		return nroCartao;
	}

	public void setNroCartao(Integer nroCartao) {
		this.nroCartao = nroCartao;
	}

	public Integer getCodigoSegurancaCartao() {
		return codigoSegurancaCartao;
	}

	public void setCodigoSegurancaCartao(Integer codigoSegurancaCartao) {
		this.codigoSegurancaCartao = codigoSegurancaCartao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

}
