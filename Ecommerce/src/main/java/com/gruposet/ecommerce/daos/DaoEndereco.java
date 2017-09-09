/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Endereco;
import com.gruposet.ecommerce.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagomessias
 * @TODO: Validations
 */
public class DaoEndereco implements Daos {
    private Database db;
    private Endereco endereco;

    public DaoEndereco(Endereco endereco) {
        this.db = new Database();
        this.endereco = endereco;
    }
    
    public DaoEndereco() {
        this.db = new Database();
    }
    
    @Override
    public void insert() {
        String query = "INSERT INTO address (user_id, rua, cep, cidade, estado, numero, padrao) VALUE (?,?,?,?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = db.getConnection().prepareStatement(query);
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
        String query = "UPDATE address SET user_id=?, rua=?, cep=?, cidade=?, estado=?, numero=?, padrao=? WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = db.getConnection().prepareStatement(query);
            stt.setInt(1, endereco.getUser_id());
            stt.setString(2, endereco.getRua());
            stt.setString(3, endereco.getCep());
            stt.setString(4, endereco.getCidade());
            stt.setString(5, endereco.getEstado());
            stt.setInt(6, endereco.getNumero());
            stt.setBoolean(7, endereco.isPadrao());
            stt.setInt(8, endereco.getId());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select() {
        String query = "SELECT * FROM address WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = db.getConnection().prepareCall(query);
            stt.setInt(1, endereco.getId());
            ResultSet rs = stt.executeQuery(query);
            while(rs.next()) {
                endereco.setUser_id(rs.getInt("user_id"));
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
    
    public static ArrayList <Endereco> listAddress(Usuario usuario) {
        Database database = new Database();
        ArrayList <Endereco> enderecos = new ArrayList<Endereco>();
        String query = "SELECT * FROM address WHERE user_id=?;";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            stt.setInt(1, usuario.getId());
            ResultSet rs = stt.executeQuery(query);
            while(rs.next()) {
                Endereco endereco = new Endereco(usuario);
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
        return enderecos;
    }
    
    public static ArrayList <Endereco> listAddress(int id) {
        Usuario user = new Usuario(id);
        return listAddress(user);
    }

    @Override
    public void delete() {
        endereco.setAtivo(false);
        endereco.setPadrao(false);
        this.update();
    }
    
}
