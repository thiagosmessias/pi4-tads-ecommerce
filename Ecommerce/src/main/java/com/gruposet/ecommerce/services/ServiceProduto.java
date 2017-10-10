package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Produto;

public abstract class ServiceProduto {

    public static boolean validaProduto(Produto pro) {
        return validaEstoque(pro.getEstoque()) || validaPalavra(pro.getModelo()) 
                || validaPalavra(pro.getMarca()) || validaPreco(pro.getPreco())
                || validaPalavra(pro.getTamanho());
    }

    private static boolean validaPalavra(String palavra) {
        return "".equals(palavra);
    }

    private static boolean validaEstoque(int estoque) {
        return estoque < 0;
    }

    private static boolean validaPreco(double preco) {
        return preco <= 0;

    }
}
