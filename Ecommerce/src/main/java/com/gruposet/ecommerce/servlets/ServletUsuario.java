package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoUsuario;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletUsuario", urlPatterns = "/user")
public class ServletUsuario extends HttpServlet {
    private InterfaceDao dao;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoUsuario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String res = "";
        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);
        final String id = request.getParameter("id");
        if (id == null || id.length() == 0) {
            dao.select("id=" + id);
            res = gson.toJson(dao.getList());
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
        } else if(request.getRequestURI().contains("update") && id != null) {
            Usuario usuario = (Usuario) this.dao.get();

            String nome = request.getParameter("nome");
            if (nome != null) {
              usuario.setNome(nome);
            }
            String apelido = request.getParameter("apelido");
            if (apelido != null) {
              usuario.setApelido(apelido);
            }
            String cpf = request.getParameter("cpf");
            if (cpf != null) {
              usuario.setCpf(cpf);
            }
            String data_nasc = request.getParameter("data_nasc");
            if (data_nasc != null) {
              usuario.setData_nasc(data_nasc);
            }
            String telefone = request.getParameter("telefone");
            if (telefone != null) {
              usuario.setTelefone(telefone);
            }
            String email = request.getParameter("email");
            if (email != null) {
              usuario.setEmail(email);
            }
            String senha = request.getParameter("senha");
            if (senha != null) {
              usuario.setSenha(senha);
            }
            if (!this.dao.get().equals(usuario)) {
                this.dao.set(usuario);
                this.dao.update();
            }
        // Insert
        } else {
            Usuario usuario = new Usuario();
            String nome = request.getParameter("nome");
            if (nome != null) {
              usuario.setNome(nome);
            }
            String apelido = request.getParameter("apelido");
            if (apelido != null) {
              usuario.setApelido(apelido);
            }
            String cpf = request.getParameter("cpf");
            if (cpf != null) {
              usuario.setCpf(cpf);
            }
            String data_nasc = request.getParameter("data_nasc");
            if (data_nasc != null) {
              usuario.setData_nasc(data_nasc);
            }
            String telefone = request.getParameter("telefone");
            if (telefone != null) {
              usuario.setTelefone(telefone);
            }
            String email = request.getParameter("email");
            if (email != null) {
              usuario.setEmail(email);
            }
            String senha = request.getParameter("senha");
            if (senha != null) {
              usuario.setSenha(senha);
            }
            this.dao.set(usuario);
            this.dao.insert();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.print("");
            out.flush();
        }
    }
}
