package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String query = "INSERT INTO usuarios (nome, apelido, cpf, celular, email, senha, acesso, ativo) VALUE (?,?,?,?,?,?,?,?);";
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
            stt.setBoolean(8, user.isAtivo());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE usuarios SET nome=?, apelido=?, celular=?, email=?, senha=? WHERE id=?";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, user.getNome());
            stt.setString(2, user.getApelido());
            stt.setString(3, user.getTelefone());
            stt.setString(4, user.getEmail());
            stt.setString(5, user.getSenha());
            stt.setInt(6, user.getId());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select() {
        String query = "SELECT * FROM usuarios WHERE id=? AND ativo=true";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            stt.setInt(1, user.getId());
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                user.setNome(rs.getString("nome"));
                user.setApelido(rs.getString("apelido"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nasc(rs.getString("data_nasc"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        user.setAtivo(false);
        this.update();
    }
}
