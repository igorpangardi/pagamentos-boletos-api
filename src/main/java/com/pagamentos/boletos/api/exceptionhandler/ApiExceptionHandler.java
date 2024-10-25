package com.pagamentos.boletos.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.stream.Collectors;

import com.pagamentos.boletos.domain.exception.BoletoIsNotValidToPayException;
import com.pagamentos.boletos.domain.exception.BoletoNotFoundException;
import com.pagamentos.boletos.domain.exception.BoletoPagamentoValorExcedidoException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BoletoIsNotValidToPayException.class)
    public ProblemDetail capturaBoletoIsNotValidToPayException(BoletoIsNotValidToPayException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(BoletoNotFoundException.class)
    public ProblemDetail capturaBoletoNotFoundException(BoletoNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(BoletoPagamentoValorExcedidoException.class)
    public ProblemDetail capturaBoletoNotFoundException(BoletoPagamentoValorExcedidoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        
        var fieldsErrors = ex.getBindingResult().getAllErrors()
            .stream()
            .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields", fieldsErrors);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }
    
}
