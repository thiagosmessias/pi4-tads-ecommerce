package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoPedido;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.helpers.Messages;
import com.gruposet.ecommerce.models.Pedido;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "ServletProduto", urlPatterns = "produto")
public class ServletPedido extends HttpServlet {

    private InterfaceDao dao;
    private String res;
    private Gson gson;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoPedido();
        res = "";
        gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        Integer usuario = null;
        if (request.getParameter("usuario") != null) {
            usuario = Integer.valueOf((String)request.getParameter("usuario"));
        }
        Integer id = null;
        if (request.getParameter("id") != null) {
            id = Integer.valueOf((String)request.getParameter("id"));
        }
        
        if (id != null) {
            dao.select("id=" + id);
            res = gson.toJson(dao.get());
        } else if (usuario != null) {
            System.out.println(usuario);
            dao.list("id_usuario=" + usuario);
            res = gson.toJson(dao.getList());
        } else if (request.getParameter("custom").isEmpty()) {
            dao.list((String)request.getParameter("custom"));
            res = gson.toJson(dao.getList());
        } else {
            Messages.writeError("Nenhum parametro conhecido foi encontrado");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        System.out.println(res);
        try (PrintWriter out = response.getWriter()) {
            out.print(res);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        BufferedReader payloadRequest = request.getReader();
        JSONObject json = new JSONObject(payloadRequest.readLine());
        
        
        Integer usuario = null;
        if (json.has("usuario")) {
            usuario = json.getInt("usuario");
        }
        
        Integer id = null;
        if (json.has("id")) {
            id = json.getInt("id");
        }
        
        if (id != null) {
            this.dao.select("id=" + id);
        }
        System.out.println("Define the action "
                + " id=" + id
                + " ususario=" + usuario
                + " delete=" + request.getParameter("delete")
                + " update=" + request.getParameter("update"));
        // Delete
        if (request.getParameter("delete") != null && request.getParameter("delete").isEmpty() && id != null) {
            System.out.println("Delete");
            this.dao.delete();

            // Update
        } else if (request.getParameter("update") != null && request.getParameter("update").isEmpty() && id != null) {
            final Pedido pedido = (Pedido) this.dao.get();
            if (json.has("status_pedido")) {
                pedido.setStatus_pedido(json.getInt("status_pedido"));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            if (!this.dao.get().equals(pedido)) {
                this.dao.update();
            }

            // Insert
        } else {
            final Pedido pedido = new Pedido();
            if (usuario != null) {
                pedido.setId_usuario(usuario);
                pedido.setStatus_pedido(0);
                this.dao.set(pedido);
                this.dao.insert();
                this.dao.select("id=LAST_INSERT_ID()");
             
                if (json.has("items")) {
                    ServletItemPedido.mountPedidoList(request, response, (Pedido)this.dao.get(), json);
                }
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.print("");
            out.flush();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
