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
    private ArrayList<Usuario> users;

    @Override
    public void insert() {
        String query = "INSERT INTO usuarios (nome, apelido, cpf, data_nasc, celular, email, senha, acesso) VALUE (?,?,?,?,?,?,?,?);";
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

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE usuarios SET nome=?, apelido=?, data_nasc=?, telefone=?, email=?, senha=? ativo=? WHERE id=?";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, user.getNome());
            stt.setString(2, user.getApelido());
            stt.setString(3, user.getData_nasc());
            stt.setString(4, user.getTelefone());
            stt.setString(5, user.getEmail());
            stt.setString(6, user.getSenha());
            stt.setBoolean(7, user.isAtivo());
            stt.setInt(8, user.getId());

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
        System.out.println(query);
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                user = new Usuario();
                user.setNome(rs.getString("nome"));
                user.setApelido(rs.getString("apelido"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nasc(rs.getString("data_nasc"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setId(rs.getInt("id"));
                user.setAtivo(rs.getBoolean("ativo"));
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
        String query = "SELECT * FROM usuarios";
        if (condition.length() != 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        PreparedStatement stt;
        this.users = new ArrayList<>();

        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                user = new Usuario();
                user.setNome(rs.getString("nome"));
                user.setApelido(rs.getString("apelido"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nasc(rs.getString("data_nasc"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                users.add(user);
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

    @Override
    public void set(Object obj) {
        this.user = (Usuario) obj;
    }

    public DaoUsuario() {
        this.database = new Database();
    }

    public boolean isCpfDuplicado(String cpf) {
        String query = "SELECT COUNT(*) FROM usuario WHERE cpf = ? AND ativo = true";
        PreparedStatement stt;
        ResultSet rs;
        int numDeCounts = 0;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, cpf);
            rs = stt.executeQuery();

            while (rs.next()) {
                numDeCounts = rs.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numDeCounts != 0;
    }

}
