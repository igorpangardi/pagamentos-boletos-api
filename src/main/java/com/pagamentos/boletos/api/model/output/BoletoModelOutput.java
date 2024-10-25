package com.pagamentos.boletos.api.model.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pagamentos.boletos.domain.model.StatusBoleto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoModelOutput {

    private Long id;
    private String codigoBarras;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private BigDecimal valorRestante;
    private StatusBoleto status;
    private String nomeRecebedor;
    private String cpfRecebedor;

}
