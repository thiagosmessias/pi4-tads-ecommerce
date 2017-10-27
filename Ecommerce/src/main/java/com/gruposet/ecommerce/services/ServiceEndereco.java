package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Endereco;

public abstract class ServiceEndereco {

    public static boolean isEnderecoValido(Endereco end) {
        return isNumeroValido(end.getNumero()) && isPalavraValido(end.getRua())
                && isPalavraValido(end.getCidade()) && isPalavraValido(end.getEstado())
                && isPalavraValido(end.getCep());
    }

    private static boolean isNumeroValido(int num) {
        return !(num <= 0);
    }

    private static boolean isPalavraValido(String palavra) {
        return !("".equals(palavra));
    }

}
