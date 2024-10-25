package com.pagamentos.boletos.domain.constants;

public class BoletoConstants {

    private BoletoConstants() {
    }

    public static final int DIAS_PARA_ADICIONAR_AO_VENCIMENTO = 10;
    public static final String BOLETO_PAGO = "Este boleto já foi pago anteriormente";
    public static final String BOLETO_ATRASADO = "Este boleto está vencido, solicite um novo para efetuar o pagamento";
    public static final String CODIGO_BARRAS_NAO_ENCONTRADO = "Não foi possível localizar um boleto com esse código de barras";
    public static final String VALOR_MAIOR_QUE_O_VALOR_DO_BOLETO = "O valor do pagamento não pode ser maior que o valor do boleto";
    
}
