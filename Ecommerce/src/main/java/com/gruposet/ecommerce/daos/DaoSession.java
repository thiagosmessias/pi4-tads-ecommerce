package com.gruposet.ecommerce.daos;

import com.gruposet.ecommerce.models.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoSession implements InterfaceDao {

    private final Database database;
    private Session session;
    private ArrayList<Session> sessions;

    @Override
    public void insert() {
        String query = "INSERT INTO session (id_usuario, hash) VALUE (?,?)";
        PreparedStatement stt;
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setInt(1, session.getId_usuario());
            stt.setString(2, session.getHash());
            stt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        String query = "UPDATE session SET hash=? WHERE id_usuario=?";
        PreparedStatement stt;
        System.out.println("Sessions id_usuario=" + session.getId_usuario());
        System.out.println(query);
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, session.getHash());
            stt.setInt(2, session.getId_usuario());
            int i = stt.executeUpdate();
            System.out.println("Rows updated " + i);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(String condition) {
        String query = "SELECT * FROM session";
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
                session = new Session();
                session.setId_usuario(rs.getInt("id_usuario"));
                session.setHash(rs.getString("hash"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        String query = "DELETE FROM session WHERE hash=?";
        PreparedStatement stt;
        System.out.println("Sessions id_usuario=" + session.getId_usuario());
        System.out.println(query);
        try {
            stt = database.getConnection().prepareStatement(query);
            stt.setString(1, session.getHash());
            stt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void list(String condition) {
        String query = "SELECT * FROM session";
        if (condition.length() != 0) {
            query += " WHERE " + condition;
        }
        query += ";";

        PreparedStatement stt;
        sessions = new ArrayList<Session>();
        System.out.println(query);
        try {
            stt = database.getConnection().prepareCall(query);
            ResultSet rs = stt.executeQuery(query);
            while (rs.next()) {
                session = new Session();
                session.setId_usuario(rs.getInt("id_usuario"));
                session.setHash(rs.getString("hash"));
                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList getList() {
        return this.sessions;
    }

    @Override
    public Object get() {
        return this.session;
    }

    @Override
    public void set(Object obj) {
        this.session = (Session) obj;
    }

    public DaoSession() {
        this.database = new Database();
    }
}
