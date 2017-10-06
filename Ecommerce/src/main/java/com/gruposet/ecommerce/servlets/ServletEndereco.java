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
        if (request.getParameter("delete").length() == 0) {
            String id = request.getParameter("id");
            Usuario user = new Usuario(Integer.valueOf(id));
            Endereco endereco = new Endereco(user);
            InterfaceDao db = new DaoEndereco(endereco);
            db.delete();

        } else if (request.getParameter("id").length() == 0) {
            String userId = request.getParameter("user_id");
            Usuario user = new Usuario(Integer.valueOf(userId));
            Endereco endereco = new Endereco(user);
            InterfaceDao db = new DaoEndereco(endereco);

            endereco.setCep(request.getParameter("cep"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));
            endereco.setNumero(Integer.valueOf(request.getParameter("numero")));
            endereco.setPadrao("1".equals(request.getParameter("padrao")));
            endereco.setRua(request.getParameter("rua"));
            db.insert();

        } else if (request.getParameter("user_id").length() == 0) {
            String id = request.getParameter("id");
            Endereco endereco = new Endereco(Integer.valueOf(id));
            InterfaceDao db = new DaoEndereco(endereco);

            endereco.setCep(request.getParameter("cep"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));
            endereco.setNumero(Integer.valueOf(request.getParameter("numero")));
            endereco.setPadrao("1".equals(request.getParameter("padrao")));
            endereco.setAtivo("1".equals(request.getParameter("ativo")));
            endereco.setRua(request.getParameter("rua"));

            db.update();
        } else {
            Messages.writeError("Can't create or update Endereco");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
