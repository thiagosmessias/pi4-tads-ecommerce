/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.models;

import java.sql.Timestamp;

/**
 *
 * @author thiago
 */

public class Pedido {
    private int id;
    private int id_usuario;
    private int status_pedido;
    private Timestamp criado_em;
    private Timestamp modificado_em;
    private float total;
    
    static final int PAGAMENTO_PENDENTE = 0;
    static final int PAGAMENTO_APROVADO = 1;
    static final int PROCESSANDO = 2;
    static final int ENVIADO = 3;
    static final int RECEBIDO = 4;
    static final int CANCELADO = 5;
    static final int DEVOLVIDO = 6;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getStatus_pedido(boolean text) {
        String status = null;
        switch(this.status_pedido) {
            case PAGAMENTO_PENDENTE:
                status = "Pagamento pendente";
                break;
            case PAGAMENTO_APROVADO:
                status = "Pagamento aprovado";
                break;
            case PROCESSANDO:
                status = "Processando";
                break;
            case ENVIADO:
                status = "Enviado";
                break;
            case RECEBIDO:
                status = "Recebido";
                break;
            case CANCELADO:
                status =  "Cancelado";
                break;
            case DEVOLVIDO:
                status = "Devolvido";
                break;  
        }
        return status;
    }
    public int getStatus_pedido() {
        return this.status_pedido;
    }
    
    public void setStatus_pedido(int status) {
        this.status_pedido = status;
    }
    
    public void setStatus_pedido(String status) {
        switch(status) {
            case "Pagamento pendente":
                this.status_pedido = PAGAMENTO_PENDENTE;
                break;
            case "Pagamento aprovado":
                this.status_pedido = PAGAMENTO_APROVADO;
                break;
            case "Processando":
                this.status_pedido = PROCESSANDO;
                break;
            case "Enviado":
                this.status_pedido = ENVIADO;
                break;
            case "Recebido":
                this.status_pedido = RECEBIDO;
                break;
            case "Cancelado":
                this.status_pedido =  CANCELADO;
                break;
            case "Devolvido":
                this.status_pedido = DEVOLVIDO;
                break;  
        }
    }
    
    public Timestamp getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Timestamp criado_em) {
        this.criado_em = criado_em;
    }

    public Timestamp getModificado_em() {
        return modificado_em;
    }

    public void setModificado_em(Timestamp modificado_em) {
        this.modificado_em = modificado_em;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
