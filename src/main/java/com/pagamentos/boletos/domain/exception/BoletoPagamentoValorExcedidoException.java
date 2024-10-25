package com.pagamentos.boletos.domain.exception;

public class BoletoPagamentoValorExcedidoException extends RuntimeException {
    public BoletoPagamentoValorExcedidoException(String message) {
        super(message);
    }
}
