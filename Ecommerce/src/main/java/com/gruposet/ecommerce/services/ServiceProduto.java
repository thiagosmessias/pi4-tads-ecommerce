package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Produto;

public abstract class ServiceProduto {

<<<<<<< current
    public static boolean validaProduto(Produto pro) {
        return validaEstoque(pro.getEstoque())
                || validaPalavra(pro.getModelo())
                || validaPalavra(pro.getMarca())
                || validaPreco(pro.getPreco());
    }

    private static boolean validaPalavra(String palavra) {
        return "".equals(palavra);
    }

    private static boolean validaEstoque(int estoque) {
        return estoque < 0;
    }

    private static boolean validaPreco(double preco) {
        return preco <= 0;
=======
    public static boolean validaProduto(Produto pro){
        return true;
    }

    private static boolean validaEstoque(int estoque){

    }

    private static boolean validaModelo(String modelo){

    }

    private static boolean validaMarca(String marca){

    }

    private static boolean validaPreco(double preco){

>>>>>>> before discard
    }
}
