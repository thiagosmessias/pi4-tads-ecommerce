package com.gruposet.ecommerce.servlets;

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletProduto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletProduto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
