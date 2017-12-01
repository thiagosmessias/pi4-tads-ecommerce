/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.models;

/**
 *
 * @author thiago
 */
public class UserSession {
    private int id_usuario;
    private String hash;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public UserSession() {
    }

    public UserSession(int id_usuario, String hash) {
        this.id_usuario = id_usuario;
        this.hash = hash;
    }
    
}
