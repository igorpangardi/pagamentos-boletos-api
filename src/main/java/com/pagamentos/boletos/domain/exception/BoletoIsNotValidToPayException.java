package com.pagamentos.boletos.domain.exception;

public class BoletoIsNotValidToPayException extends RuntimeException{
    public BoletoIsNotValidToPayException(String message) {
        super(message);
    }
}
