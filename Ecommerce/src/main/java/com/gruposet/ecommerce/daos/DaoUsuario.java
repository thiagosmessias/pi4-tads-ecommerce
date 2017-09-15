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
    private final Database database;
    private Usuario cli;

    public DaoUsuario(Usuario user) {
        database = new Database();
        cli = user;
    }

    public void setCli(Usuario cli) {
        this.cli = cli;
    }
    
    public Usuario getCli() {
        return cli;
    }

     @Override
    public void insert() {
        String query = "INSERT INTO Cliente (nome, apelido, cpf, celular, email, senha, acesso, ativo) VALUE (?,?,?,?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);

            stt.setString(1, cli.getName());
            stt.setString(2, cli.getNickname());
            stt.setString(3, cli.getCpf());
            stt.setString(4, cli.getPhone());
            stt.setString(5, cli.getEmail());
            stt.setString(6, cli.getPassword());
            stt.setString(7, cli.getAccess());
            stt.setBoolean(8, cli.isEnable());

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
