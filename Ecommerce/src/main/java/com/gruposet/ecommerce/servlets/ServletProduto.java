package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoProduto;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.models.Produto;
import com.gruposet.ecommerce.services.ServiceProduto;
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
            dao.select("id=" + id);
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
        Integer id = Integer.valueOf(request.getParameter("id"));
        if (id != null) {
            this.dao.select("id=" + id);
        }

        // Delete
        if (request.getRequestURI().contains("delete") && id != null) {
            this.dao.delete();
            response.setStatus(HttpServletResponse.SC_OK);

            // Update
        } else if (request.getRequestURI().contains("update") && id != null) {
            Produto pro = (Produto) this.dao.get();
            pro.setDescricao(request.getParameter("descricao"));
            pro.setMarca(request.getParameter("marca"));
            pro.setModelo(request.getParameter("modelo"));
            pro.setTamanho(request.getParameter("tamanho"));
            pro.setEstoque(Integer.valueOf(request.getParameter("estoque")));
            pro.setPreco(Double.valueOf(request.getParameter("preco")));

            if (this.dao.get().equals(pro)) {
                if (ServiceProduto.isProdutoValido(pro)) {
                    this.dao.set(pro);
                    this.dao.update();
                }
            }

            // Insert
        } else {
            String modelo = request.getParameter("modelo");
            String marca = request.getParameter("marca");
            String descricao = request.getParameter("descricao");
            String tamanho = request.getParameter("tamanho");
            int estoque = Integer.valueOf(request.getParameter("estoque"));
            double preco = Double.valueOf(request.getParameter("preco"));

            Produto pro = new Produto(estoque, modelo, marca, descricao, tamanho, preco);

            if (ServiceProduto.isProdutoValido(pro)) {
                this.dao.set(pro);
                this.dao.insert();
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
