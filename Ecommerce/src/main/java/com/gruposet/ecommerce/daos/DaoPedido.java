package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPedido implements InterfaceDao {

    private final Database database;
    private Pedido pedido;
    private ArrayList<Pedido> pedidos;
    
    public DaoPedido() {
        this.database = new Database();
    }
    
    @Override
    public void insert() {
            String query = "INSERT INTO pedidos ("
                    + " id_usuario,"
                    + " status_pedido,"
                    + " criado_em,"
                    + " modificado_em) VALUE (?,?,NOW(),NOW());";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, pedido.getId_usuario());
            stt.setInt(2, pedido.getStatus_pedido());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE pedidos SET status_pedido=?"
                + " modificado_em=NOW() WHERE id=?;";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, pedido.getId_usuario());
            stt.setInt(2, pedido.getStatus_pedido());
            stt.setInt(3, pedido.getId());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM pedidos";
        if (condition != null && condition.length() > 0) {
            query += " WHERE " + condition;
        }
        query += ";";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareCall(query);
            stt.setInt(1, pedido.getId());
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                this.pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setStatus_pedido(rs.getInt("status_pedido"));
                pedido.setCriado_em(rs.getTimestamp("criado_em"));
                pedido.setModificado_em(rs.getTimestamp("modificado_em"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        this.pedido.setStatus_pedido("Cancelado");
        this.update();
    }

    @Override
    public void list(String condition) {
        pedidos = new ArrayList<>();
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
                this.pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setStatus_pedido(rs.getInt("status_pedido"));
                pedido.setCriado_em(rs.getTimestamp("criado_em"));
                pedido.setModificado_em(rs.getTimestamp("modificado_em"));
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.pedidos;
    }

    @Override
    public Object get() {
        return this.pedido;
    }

    @Override
    public void set(Object obj) {
        this.pedido = (Pedido) obj;
    }
}