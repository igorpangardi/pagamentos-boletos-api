package com.pagamentos.boletos.domain.exception;

public class BoletoNotFoundException extends RuntimeException {
    public BoletoNotFoundException(String message) {
        super(message);
    }
}
