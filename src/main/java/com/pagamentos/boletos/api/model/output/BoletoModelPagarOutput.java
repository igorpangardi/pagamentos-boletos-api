package com.pagamentos.boletos.api.model.output;

import java.math.BigDecimal;

import com.pagamentos.boletos.domain.model.StatusBoleto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoModelPagarOutput {

    private String codigoBarras;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private BigDecimal valorRestante;
    private StatusBoleto status;
    private String nomePagador;
    private String cpfPagador;
    
}
