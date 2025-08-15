package Projeto_Poesia.BackEnd.Service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoUtil {

    private static final Pattern CARACTERES_INVALIDOS = 
        Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚâêîôÂÊÎÔãõÃÕçÇ\\s.,!?;:()-]");

    public static void validarCaracteres(String texto, String campo) {
        if (texto == null) return;
        
        Matcher matcher = CARACTERES_INVALIDOS.matcher(texto);
        if (matcher.find()) {
            throw new IllegalArgumentException(
                String.format("O campo %s contém caracteres especiais não permitidos: '%s'", 
                campo, matcher.group()));
        }
    }
    
}
