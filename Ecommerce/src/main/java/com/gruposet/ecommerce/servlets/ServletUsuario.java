package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoUsuario;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.models.Usuario;
import com.gruposet.ecommerce.services.ServiceUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletUsuario", urlPatterns = "usuario")
public class ServletUsuario extends HttpServlet {

    private InterfaceDao dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } else if (request.getRequestURI().contains("update") && id != null) {
            Usuario usuario = (Usuario) this.dao.get();
            usuario.setNome(request.getParameter("nome"));
            usuario.setApelido(request.getParameter("apelido"));
            usuario.setCpf(request.getParameter("cpf"));
            usuario.setData_nasc(request.getParameter("data_nasc"));
            usuario.setTelefone(request.getParameter("telefone"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));

            if (this.dao.get().equals(usuario)) {
                if (ServiceUsuario.isUsuarioValido(usuario)) {
                    this.dao.set(usuario);
                    this.dao.update();
                }

            }
            
            // Insert
        } else {
            String nome = request.getParameter("nome");
            String apelido = request.getParameter("apelido");
            String cpf = request.getParameter("cpf");
            String data_nasc = request.getParameter("data_nasc");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            Usuario usuario = new Usuario(nome, apelido, cpf, data_nasc, telefone, email, senha);

            if (ServiceUsuario.isUsuarioValido(usuario)) {
                this.dao.set(usuario);
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
