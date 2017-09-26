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
    private Produto produto;
    private ArrayList<Produto>produtos;

    public DaoProduto(Produto pro) {
        database = new Database();
        this.produto = pro;
    }
    
    public DaoProduto() {
        database = new Database();
    }
    
    @Override
    public void insert() {
        String query = "INSERT INTO produtos (estoque, modelo, marca, descricao, preco, ativo) VALUE (?,?,?,?,?,?)";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, produto.getEstoque());
            stt.setString(2, produto.getModelo());
            stt.setString(3, produto.getMarca());
            stt.setString(4, produto.getDescricao());
            stt.setDouble(5, produto.getPreco());
            stt.setBoolean(6, produto.isAtivo());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE produtos SET estoque=?, modelo=?, marca=?, descricao=?, preco=? WHERE id=?";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, produto.getEstoque());
            stt.setString(2, produto.getModelo());
            stt.setString(3, produto.getMarca());
            stt.setString(4, produto.getDescricao());
            stt.setDouble(5, produto.getPreco());
            stt.setInt(6, produto.getId());

            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM produtos";
        if (condition != null && condition.length() > 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                produto.setEstoque(rs.getInt("estoque"));
                produto.setModelo(rs.getString("modelo"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        produto.setAtivo(false);
        this.update();
    }
    
    @Override
    public void list(String condition) {
        
        String query = "SELECT * FROM produtos";
        if (condition.length() == 0) {
            query += " WHERE " + condition;
        }
        query += "query";
        
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while(rs.next()){
                produto.setDescricao(rs.getString("descricao"));
                produto.setModelo(rs.getString("modelo"));
                produto.setMarca(rs.getString("marca"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.produtos;
    }

    @Override
    public Object get() {
        return this.produto;
    }

    @Override
    public void set(Object obj) {
        this.produto = (Produto) obj;
    }
}
