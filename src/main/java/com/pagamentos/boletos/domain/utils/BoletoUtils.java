package com.pagamentos.boletos.domain.utils;
import java.util.Random;


public class BoletoUtils {
    
    private BoletoUtils() {
    }

    public static String gerarCodigoBarras() {
        StringBuilder codigoBarras = new StringBuilder(44);
        Random random = new Random();
        
        for (int i = 0; i < 44; i++) {
            codigoBarras.append(random.nextInt(10));
        }
        
        return codigoBarras.toString();
    }
    
}
