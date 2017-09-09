package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuario implements InterfaceDao {
    private final Database database;
    private Usuario user;

    public DaoUsuario(Usuario user) {
        database = new Database();
        this.user = user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public Usuario getUser() {
        return user;
    }

     @Override
    public void insert() {
        String query = "INSERT INTO Cliente (nome, apelido, cpf, celular, email, senha, acesso, ativo) VALUE (?,?,?,?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);

            stt.setString(1, user.getNome());
            stt.setString(2, user.getApelido());
            stt.setString(3, user.getCpf());
            stt.setString(4, user.getTelefone());
            stt.setString(5, user.getEmail());
            stt.setString(6, user.getSenha());
            stt.setString(7, user.getAcesso());
            stt.setBoolean(8, user.isEnable());

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
