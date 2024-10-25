package com.pagamentos.boletos.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoModelInput {
    
    @NotNull
    private BigDecimal valor;

    @NotBlank
    @Size(max = 80)
    private String nomeRecebedor;

    @NotBlank
    @Size(max = 80)
    private String cpfRecebedor;

    @NotBlank
    @Size(max = 100)
    private String descricao;
}
