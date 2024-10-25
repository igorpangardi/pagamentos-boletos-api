package com.pagamentos.boletos.api.model.input;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoModelPagarInput {

    @NotBlank
    private String codigoBarras;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Size(max = 80)
    private String nomePagador;

    @NotNull
    @Size(max = 80)
    private String cpfPagador;

}
