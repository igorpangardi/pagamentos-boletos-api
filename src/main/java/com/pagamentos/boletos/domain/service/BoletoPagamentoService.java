package com.pagamentos.boletos.domain.service;

import static com.pagamentos.boletos.domain.constants.BoletoConstants.BOLETO_ATRASADO;
import static com.pagamentos.boletos.domain.constants.BoletoConstants.BOLETO_PAGO;
import static com.pagamentos.boletos.domain.constants.BoletoConstants.CODIGO_BARRAS_NAO_ENCONTRADO;
import static com.pagamentos.boletos.domain.constants.BoletoConstants.VALOR_MAIOR_QUE_O_VALOR_DO_BOLETO;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.pagamentos.boletos.domain.exception.BoletoIsNotValidToPayException;
import com.pagamentos.boletos.domain.exception.BoletoNotFoundException;
import com.pagamentos.boletos.domain.exception.BoletoPagamentoValorExcedidoException;
import com.pagamentos.boletos.domain.model.Boleto;
import com.pagamentos.boletos.domain.model.StatusBoleto;
import com.pagamentos.boletos.domain.repository.BoletoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoletoPagamentoService {

    private final BoletoRepository repository;

    public Boleto pagar(Boleto boletoPagamento) {
        
        Boleto boleto = repository.findByCodigoBarras(boletoPagamento.getCodigoBarras())
            .orElseThrow(() -> new BoletoNotFoundException(CODIGO_BARRAS_NAO_ENCONTRADO));

        return switch (boleto.getStatus()) {

            case PENDENTE -> 
                processaBoleto(boleto, boletoPagamento, isPagamentoParcial(boleto.getValor(), boletoPagamento.getValor()));

            case PAGO_PARCIAL -> 
                processaBoleto(boleto, boletoPagamento, isPagamentoParcial(boleto.getValorRestante(), boletoPagamento.getValor()));

            case PAGO -> 
                throw new BoletoIsNotValidToPayException(BOLETO_PAGO);

            case ATRASADO -> 
                throw new BoletoIsNotValidToPayException(BOLETO_ATRASADO);

            default -> null;
        };
    }

    private boolean isPagamentoParcial(BigDecimal valorBoleto, BigDecimal valorDoPagamento) {

        int comparator = valorDoPagamento.compareTo(valorBoleto);

        if(comparator > 0) {
            throw new BoletoPagamentoValorExcedidoException(VALOR_MAIOR_QUE_O_VALOR_DO_BOLETO);
        } 

        return comparator < 0;
    }

    private Boleto processaBoleto(Boleto boleto, Boleto boletoPagamento, boolean isPagamentoParcial) {
        
        if(isPagamentoParcial) {
            processaPagamentoParcial(boleto, boletoPagamento.getValor());
        } else {
            processaPagamentoTotal(boleto);
        }

        boleto.setNomePagador(boletoPagamento.getNomePagador());
        boleto.setCpfPagador(boletoPagamento.getCpfPagador());

        return repository.save(boleto);
    }

    private void processaPagamentoParcial(Boleto boleto, BigDecimal valorPagamento) {
        boleto.setValorPago(valorPagamento);
        boleto.setValorRestante(boleto.getValorRestante().subtract(valorPagamento));
        boleto.setStatus(StatusBoleto.PAGO_PARCIAL);
    }

    private void processaPagamentoTotal(Boleto boleto) {
        boleto.setValorPago(boleto.getValor());
        boleto.setValorRestante(BigDecimal.ZERO); // Nenhum valor restante
        boleto.setStatus(StatusBoleto.PAGO);
    }
}
