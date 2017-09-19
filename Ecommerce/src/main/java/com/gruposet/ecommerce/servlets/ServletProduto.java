package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoProduto;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.models.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProduto", urlPatterns = "produto")
public class ServletProduto extends HttpServlet {
    private InterfaceDao dao;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoProduto();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String res = "";
        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);
        if (request.getParameter("id") != null) {
            if (request.getParameter("id").length() == 0) {
                System.out.println("Erro");
            }
            final String end_id = request.getParameter("id");
            final int id = Integer.parseInt(end_id);
            dao.select("id=" + end_id);
            res = gson.toJson(dao.get());
        } else {
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
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        int estoque = Integer.valueOf(request.getParameter("estoque"));
        double preco = Double.valueOf(request.getParameter("preco"));
        
        Produto pro = new Produto(estoque, modelo, marca, descricao, preco, true);
        InterfaceDao db = new DaoProduto(pro);
        
        db.insert();
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
