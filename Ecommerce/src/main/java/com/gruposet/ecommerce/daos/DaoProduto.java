/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class DaoProduto implements InterfaceDao {

    private final Database database;
    private Produto pro;

    public DaoProduto(Produto pro) {
        database = new Database();
        this.pro = pro;
    }

    public void setPro(Produto pro) {
        this.pro = pro;
    }

    public Produto getPro() {
        return pro;
    }

    @Override
    public void insert() {
        String query = "INSERT INTO produtos (estoque, modelo, marca, descricao, preco, ativo) VALUE (?,?,?,?,?,?)";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, pro.getEstoque());
            stt.setString(2, pro.getModelo());
            stt.setString(3, pro.getMarca());
            stt.setString(4, pro.getDescricao());
            stt.setDouble(5, pro.getPreco());
            stt.setBoolean(6, pro.isAtivo());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE produto SET estoque=?, modelo=?, marca=?, descricao=?, preco=? WHERE id=?";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, pro.getEstoque());
            stt.setString(2, pro.getModelo());
            stt.setString(3, pro.getMarca());
            stt.setString(4, pro.getDescricao());
            stt.setDouble(5, pro.getPreco());
            stt.setInt(6, pro.getId());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select() {
        String query = "SELECT * FROM produtos WHERE id=? AND ativo=true";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            stt.setInt(1, pro.getId());
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                pro.setEstoque(rs.getInt("estoque"));
                pro.setModelo(rs.getString("modelo"));
                pro.setMarca(rs.getString("marca"));
                pro.setDescricao(rs.getString("descricao"));
                pro.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        pro.setAtivo(false);
        this.update();
    }

    public ArrayList<Produto> listar() {
        ArrayList<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while(rs.next()){
                pro.setDescricao(rs.getString("descricao"));
                pro.setModelo(rs.getString("modelo"));
                pro.setMarca(rs.getString("marca"));
                pro.setPreco(rs.getDouble("preco"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

}
