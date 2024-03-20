package br.com.projeto.api.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    
    public String getDetailsError(String msgError) {
        String mensagemErro = msgError;

        String padrao = "\\[.*?\\];";

        // Remove a parte específica da mensagem de erro
        String mensagemFormatada = mensagemErro.replaceAll(padrao, "");

        System.out.println("Detalhe do erro: " + mensagemFormatada);
        return mensagemFormatada;
    }
}
