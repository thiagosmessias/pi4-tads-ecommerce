package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoEndereco;
import com.gruposet.ecommerce.helpers.Messages;
import com.gruposet.ecommerce.models.Endereco;
import com.gruposet.ecommerce.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.services.ServiceEndereco;

public class ServletEndereco extends HttpServlet {

    private InterfaceDao dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoEndereco();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String res = "";
        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);

        if (request.getParameter("user_id") != null) {
            if (request.getParameter("user_id").length() == 0) {
                System.out.print("Erro");
            }

            final String userId = request.getParameter("user_id");
            final int user_id = Integer.parseInt(userId);
            dao.list("user_id=" + userId);
            res = gson.toJson(dao.getList());

        } else if (request.getParameter("id") != null) {
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
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        Integer id = Integer.valueOf(request.getParameter("id"));
        if (id != null) {
            this.dao.select("id=" + id);
        }

        // Delete
        if (request.getRequestURI().contains("delete") && user_id != null) {
            this.dao.delete();
            response.setStatus(HttpServletResponse.SC_OK);

            // Update
        } else if (request.getRequestURI().contains("update") && user_id != null) {
            Endereco endereco = (Endereco) this.dao.get();

            endereco.setUser_id(user_id);
            endereco.setCep(request.getParameter("cep"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));
            endereco.setNumero(Integer.valueOf(request.getParameter("numero")));
            endereco.setPadrao("1".equals(request.getParameter("padrao")));
            endereco.setRua(request.getParameter("rua"));

            if (this.dao.get().equals(endereco)) {
                if (ServiceEndereco.isEnderecoValido(endereco)) {
                    this.dao.set(endereco);
                    this.dao.update();
                }
            }

            // Insert
        } else {
            int numero = Integer.valueOf(request.getParameter("numero"));
            String rua = request.getParameter("rua");
            String estado = request.getParameter("estado");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            boolean padrao = "1".equals(request.getParameter("padrao"));

            Endereco endereco = new Endereco(user_id, numero, rua, estado, cidade, cep, padrao);

            if (ServiceEndereco.isEnderecoValido(endereco)) {
                this.dao.set(endereco);
                this.dao.insert();
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.print("");
            out.flush();
        }
    }
}
