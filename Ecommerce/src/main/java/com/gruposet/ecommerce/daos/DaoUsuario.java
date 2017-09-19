package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuario implements InterfaceDao {

    private final Database database;
    private Usuario user;
    private ArrayList <Usuario> users;
    
    public DaoUsuario() {
        database = new Database();
    }
    
    public DaoUsuario(Usuario user) {
        database = new Database();
        this.user = user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public void insert() {
        String query = "INSERT INTO usuarios (nome, apelido, cpf, data_nasc, celular, email, senha, acesso, ativo) VALUE (?,?,?,?,?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);

            stt.setString(1, user.getNome());
            stt.setString(2, user.getApelido());
            stt.setString(3, user.getCpf());
            stt.setString(4, user.getData_nasc());
            stt.setString(5, user.getTelefone());
            stt.setString(6, user.getEmail());
            stt.setString(7, user.getSenha());
            stt.setString(8, user.getPerfil());
            stt.setBoolean(9, user.isAtivo());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE usuarios SET nome=?, apelido=?, data_nasc=?, telefone=?, email=?, senha=? WHERE id=?";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, user.getNome());
            stt.setString(2, user.getApelido());
            stt.setString(3, user.getData_nasc());
            stt.setString(4, user.getTelefone());
            stt.setString(5, user.getEmail());
            stt.setString(6, user.getSenha());
            stt.setInt(7, user.getId());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM usuarios";
        if (condition != null && condition.length() > 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
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
    
    @Override
    public void list(String condition) {
        Database db = new Database();
        String query = "SELECT * FROM usuarios";
        if (condition.length() != 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        PreparedStatement stt;
        this.users = new ArrayList<>();

        try {
            stt = db.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                users.add(new Usuario(
                        rs.getInt("id"), 
                        rs.getString("nome"),
                        rs.getString("apelido"),
                        rs.getString("cpf"),
                        rs.getString("data_nasc"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getBoolean("ativo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.users;
    }

    @Override
    public Object get() {
        return this.user;
    }
}
