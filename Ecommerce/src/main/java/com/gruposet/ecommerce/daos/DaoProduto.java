package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProduto implements InterfaceDao {

    private final Database database;
    private Produto produto;
    private ArrayList<Produto> produtos;

    @Override
    public void insert() {
        String query = "INSERT INTO produtos (estoque, modelo, marca, descricao, tamanho, preco) VALUE (?,?,?,?,?,?)";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, produto.getEstoque());
            stt.setString(2, produto.getModelo());
            stt.setString(3, produto.getMarca());
            stt.setString(4, produto.getDescricao());
            stt.setString(5, produto.getTamanho());
            stt.setDouble(6, produto.getPreco());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE produtos SET estoque=?, modelo=?, marca=?, descricao=?, tamanho=?, preco=?, ativo=? WHERE id=?";
        PreparedStatement stt;
        System.out.println("Produtos id=" + produto.getId());
        System.out.println(query);
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, produto.getEstoque());
            stt.setString(2, produto.getModelo());
            stt.setString(3, produto.getMarca());
            stt.setString(4, produto.getDescricao());
            stt.setString(5, produto.getTamanho());
            stt.setDouble(6, produto.getPreco());
            stt.setBoolean(7, produto.isAtivo());
            stt.setInt(8, produto.getId());
            int i = stt.executeUpdate();
            System.out.println("Rows updated " + i);
            
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
        System.out.println(query);
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                produto = new Produto();
                produto.setEstoque(rs.getInt("estoque"));
                produto.setModelo(rs.getString("modelo"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTamanho(rs.getString("tamanho"));
                produto.setId(rs.getInt("id"));
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
        if (condition.length() != 0) {
            query += " WHERE " + condition;
        }
        query += ";";

        PreparedStatement stt;
        produtos = new ArrayList<Produto>();
        System.out.println(query);
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setModelo(rs.getString("modelo"));
                produto.setMarca(rs.getString("marca"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTamanho(rs.getString("tamanho"));
                produto.setEstoque(rs.getInt("estoque"));
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

    public DaoProduto() {
        this.database = new Database();
    }
}
