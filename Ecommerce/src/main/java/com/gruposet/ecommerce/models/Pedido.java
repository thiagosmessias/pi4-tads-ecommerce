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
    private String status_pedido;
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

    public int getStatus_pedido() {
        int status = 0;
        switch(this.status_pedido) {
            case "Pagamento pendente":
                status = PAGAMENTO_PENDENTE;
                break;
            case "Pagamento aprovado":
                status= PAGAMENTO_APROVADO;
                break;
            case "Processando":
                status= PROCESSANDO;
                break;
            case "Enviado":
                status = ENVIADO;
                break;
            case "Recebido":
                status = RECEBIDO;
                break;
            case "Cancelado":
                status =  CANCELADO;
                break;
            case "Devolvido":
                status = DEVOLVIDO;
                break;  
        }
        return status;
    }
    
    public void setStatus_pedido(int status) {
        switch(status) {
            case PAGAMENTO_PENDENTE:
                this.status_pedido = "Pagamento pendente";
                break;
            case PAGAMENTO_APROVADO:
                this.status_pedido = "Pagamento aprovado";
                break;
            case PROCESSANDO:
                this.status_pedido = "Processando";
                break;
            case ENVIADO:
                this.status_pedido = "Enviado";
                break;
            case RECEBIDO:
                this.status_pedido = "Recebido";
                break;
            case CANCELADO:
                this.status_pedido =  "Cancelado";
                break;
            case DEVOLVIDO:
                this.status_pedido = "Devolvido";
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
