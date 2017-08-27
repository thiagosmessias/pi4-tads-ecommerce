/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagomessias
 */
public class DaoUsuario implements Daos {
    private Database db;
    private Usuario user;

    public DaoUsuario() {
        db = new Database();
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void insert() {
        String query = "INSERT INTO users (nome, sobrenome, telefone, cep, perfil) VALUE (?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = db.getConnection().prepareStatement(query);
            stt.setString(1, user.getNome());
            stt.setString(2, user.getSobrenome());
            stt.setString(3, user.getTelefone());
            stt.setString(4, user.getCep());
            stt.setInt(5, 1);
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
