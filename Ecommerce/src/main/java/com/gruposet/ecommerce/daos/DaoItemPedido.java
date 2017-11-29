package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.ItemPedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoItemPedido implements InterfaceDao {

    private final Database database;
    private ItemPedido itemPedido;
    private ArrayList<ItemPedido> itemsPedido;
    
    public DaoItemPedido() {
        this.database = new Database();
    }
    
    @Override
    public void insert() {
            String query = "INSERT INTO items_pedido ("
                    + " id_pedido,"
                    + " id_produto,"
                    + " preco,"
                    + " quantidade) VALUE (?,?,?,?);";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, itemPedido.getPedido_id());
            stt.setInt(2, itemPedido.getProduto_id());
            stt.setFloat(3, itemPedido.getPreco());
            stt.setInt(4, itemPedido.getQuantidade());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE items_pedido SET preco=?, quantidade=? WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setFloat(1, itemPedido.getPreco());
            stt.setInt(2, itemPedido.getQuantidade());
            stt.setInt(3, itemPedido.getId());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM items_pedido";
        if (condition != null && condition.length() > 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setPedido_id(rs.getInt("pedido_id"));
                itemPedido.setProduto_id(rs.getInt("produto_id"));
                itemPedido.setPreco(rs.getFloat("preco"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        String query = "DELETE FROM items_pedido WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, itemPedido.getId());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void list(String condition) {
        itemsPedido = new ArrayList<>();
        String query = "SELECT * FROM pedidos";
        if (condition.length() == 0) {
            query += " WHERE ";
        }
        query += ";";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {                
                itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setPedido_id(rs.getInt("pedido_id"));
                itemPedido.setProduto_id(rs.getInt("produto_id"));
                itemPedido.setPreco(rs.getFloat("preco"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itemsPedido.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.itemsPedido;
    }

    @Override
    public Object get() {
        return this.itemPedido;
    }

    @Override
    public void set(Object obj) {
        this.itemPedido = (ItemPedido) obj;
    }
}
