package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoItemPedido;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.helpers.Messages;
import com.gruposet.ecommerce.models.ItemPedido;
import com.gruposet.ecommerce.models.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "ServletProduto", urlPatterns = "produto")
public class ServletItemPedido extends HttpServlet {

    private InterfaceDao dao;
    String res;
    Gson gson;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoItemPedido();
        res = "";
        gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        Integer produto = Integer.valueOf((String)request.getParameter("produto"));
        Integer pedido = Integer.valueOf((String)request.getParameter("pedido"));
        
        if (pedido != null) {
            dao.select("id_pedido=" + pedido);
            res = gson.toJson(dao.get());
        } else if (produto != null) {
            dao.list("id_produto=" + produto);
            res = gson.toJson(dao.getList());
        } else {
            Messages.writeError("Nenhum parametro conhecido foi encontrado");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(res);
            out.flush();
        }
    }
    public void mountPedidoList(HttpServletRequest request, HttpServletResponse response, Pedido pedido, JSONObject json) throws ServletException, IOException {

        if (pedido != null && json.has("items")) {
            JSONArray jarray = json.getJSONArray("items");
            for (Object obj : jarray) {
                JSONObject jobj = (JSONObject)obj;
                ItemPedido item = new ItemPedido();
                if (jobj.has("quantidade")) {
                    item.setQuantidade(jobj.getInt("quantidade"));
                }
                if (jobj.has("preco")) {
                    item.setPreco(jobj.getFloat("preco"));
                }
                if (jobj.has("id")) {
                    item.setProduto_id(jobj.getInt("id"));
                }
                item.setPedido_id(pedido.getId());
                dao.set((ItemPedido)item);
                dao.insert();
            }
            response.setStatus(HttpServletResponse.SC_CREATED);
            
        } else {
            Messages.writeError("Nenhum parametro conhecido foi encontrado");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(res);
            out.flush();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);        
        
        Integer produto = Integer.valueOf((String)request.getParameter("produto"));
        Integer pedido = Integer.valueOf((String)request.getParameter("pedido"));
        
        if (pedido != null && produto != null ) {
            this.dao.select("pedido_id=" + pedido + " AND produto_id=" + produto);
        } if (pedido != null) {
            this.dao.list("pedido_id=" + pedido);
        } else if (produto != null) {
            this.dao.list("protudo_id=" + produto);
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
