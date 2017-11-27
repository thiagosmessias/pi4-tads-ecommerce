package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEndereco implements InterfaceDao {

    private final Database database;
    private Endereco endereco;
    private ArrayList<Endereco> enderecos;

    @Override
    public void insert() {
        String query = "INSERT INTO enderecos (id_usuario, rua, cep, cidade, estado, numero, padrao) VALUE (?,?,?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, endereco.getUser_id());
            stt.setString(2, endereco.getRua());
            stt.setString(3, endereco.getCep());
            stt.setString(4, endereco.getCidade());
            stt.setString(5, endereco.getEstado());
            stt.setInt(6, endereco.getNumero());
            stt.setBoolean(7, endereco.isPadrao());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE enderecos SET id_usuario=?, rua=?, cep=?, cidade=?, estado=?, numero=?, padrao=?, ativo=? WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, endereco.getUser_id());
            stt.setString(2, endereco.getRua());
            stt.setString(3, endereco.getCep());
            stt.setString(4, endereco.getCidade());
            stt.setString(5, endereco.getEstado());
            stt.setInt(6, endereco.getNumero());
            stt.setBoolean(7, endereco.isPadrao());
            stt.setBoolean(8, endereco.isAtivo());
            stt.setInt(9, endereco.getId());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM enderecos";
        if (condition != null && condition.length() > 0) {
            query += " WHERE " + condition;
        }
//        System.out.println(query);
        query += ";";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            stt.setInt(1, endereco.getId());
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                endereco = new Endereco();
                endereco.setUser_id(rs.getInt("usuario_id"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setAtivo(rs.getBoolean("ativo"));
                endereco.setPadrao(rs.getBoolean("padrao"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        endereco.setAtivo(false);
        endereco.setPadrao(false);
        this.update();
    }

    @Override
    public void list(String condition) {
        enderecos = new ArrayList<Endereco>();
        String query = "SELECT * FROM enderecos";
        if (condition.length() == 0) {
            query += " WHERE ";
        }
        query += ";";
        System.out.println(query);
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                endereco = new Endereco();
                endereco.setUser_id(rs.getInt("id_usuario"));
                endereco.setId(rs.getInt("id"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setAtivo(rs.getBoolean("ativo"));
                endereco.setPadrao(rs.getBoolean("padrao"));
                enderecos.add(endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.enderecos;
    }

    @Override
    public Object get() {
        return this.endereco;
    }

    @Override
    public void set(Object obj) {
        this.endereco = (Endereco) obj;
    }

    public DaoEndereco() {
        this.database = new Database();
    }
}
