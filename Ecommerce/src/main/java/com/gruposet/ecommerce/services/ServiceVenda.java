package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Venda;

public abstract class ServiceVenda {

    public static boolean isVendaValida(Venda venda) {
        return true;
    }

    public static boolean isIdProdutoValido(int id) {
        return !(id <= 0);
    }
}
