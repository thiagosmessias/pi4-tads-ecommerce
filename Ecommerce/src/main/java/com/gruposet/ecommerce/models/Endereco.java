/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.models;

/**
 *
 * @author thiagomessias
 */
public class Endereco {
    private int id;
    private int user_id;
    private int numero;
    private String rua;
    private String estado;
    private String cidade;
    private String cep;
    private boolean padrao;
    private boolean ativo;
    
    public Endereco() {}
    
    public Endereco(int id) {
        this.id = id;
    }
    
    public Endereco(Usuario usuario) {
        this.user_id = usuario.getId();
    }

    public Endereco(int user_id, int numero, String rua, String estado, String cidade, String cep, boolean padrao, boolean ativo) {
        this.user_id = user_id;
        this.numero = numero;
        this.rua = rua;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.padrao = padrao;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}