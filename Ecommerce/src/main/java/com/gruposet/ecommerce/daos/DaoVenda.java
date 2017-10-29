package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoVenda implements InterfaceDao {
    
    private final Database database;
    private Venda venda;
    private ArrayList<Venda> vendas;
    
    @Override
    public void insert() {
        String query = "";
        PreparedStatement stt;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        venda.setAtivo(false);
        this.update();
    }

    @Override
    public void list(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getList() {
        return this.vendas;
    }

    @Override
    public Object get() {
        return this.venda;
    }

    @Override
    public void set(Object obj) {
        this.venda = (Venda) obj;
    }
    
    public DaoVenda(){
        this.database =  new Database();
    }

}
