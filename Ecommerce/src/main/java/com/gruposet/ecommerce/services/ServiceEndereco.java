package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Endereco;

public abstract class ServiceEndereco {

    public static boolean validaEndereco(Endereco end) {
        return validaNumero(end.getNumero())
                || validaPalavra(end.getRua())
                || validaPalavra(end.getEstado())
                || validaPalavra(end.getEstado())
                || validaPalavra(end.getCep());
    }

    private static boolean validaNumero(int num) {
        return num <= 0;
    }

    private static boolean validaPalavra(String palavra) {
        return "".equals(palavra);
    }

}
