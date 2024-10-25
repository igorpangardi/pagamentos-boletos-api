package com.pagamentos.boletos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String codigoBarras;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private BigDecimal valorPago;
    private BigDecimal valorRestante;

    @Enumerated(EnumType.STRING)    
    private StatusBoleto status;
    private String nomeRecebedor;
    private String cpfRecebedor;
    private String nomePagador;
    private String cpfPagador;
    private BigDecimal juros;
    private BigDecimal multa;
    private BigDecimal desconto;
    
}
