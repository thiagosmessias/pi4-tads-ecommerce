package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Produto;

public abstract class ServiceProduto {

    public static boolean isProdutoValido(Produto pro) {
        return isEstoqueValido(pro.getEstoque()) && isPalavraValido(pro.getModelo()) 
                && isPalavraValido(pro.getMarca()) && isPrecoValido(pro.getPreco())
                && isPalavraValido(pro.getTamanho());
    }

    private static boolean isPalavraValido(String palavra) {
        return !("".equals(palavra));
    }

    private static boolean isEstoqueValido(int estoque) {
        return !(estoque < 0);
    }

    private static boolean isPrecoValido(double preco) {
        return !(preco <= 0);

    }
}
