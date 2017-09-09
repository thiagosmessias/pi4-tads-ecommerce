package com.gruposet.ecommerce.servlets;

import com.gruposet.ecommerce.daos.DaoUsuario;
import com.gruposet.ecommerce.models.Usuario;
import com.gruposet.ecommerce.services.ServiceUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletUsuario", urlPatterns = "/user")
public class ServletUsuario extends HttpServlet {

    private DaoUsuario dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String apelido = request.getParameter("apelido");
        String cpf = request.getParameter("cpf");
        String data_nasc = request.getParameter("data_nasc");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        
        Usuario user = new Usuario(nome, apelido, cpf, data_nasc, telefone, email, senha, true);
        //boolean b = ServiceUsuario.validateUser(user);
        dao = new DaoUsuario(user);
        dao.insert();
    }
}
