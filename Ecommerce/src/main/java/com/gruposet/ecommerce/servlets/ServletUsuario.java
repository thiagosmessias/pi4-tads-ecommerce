/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String name = request.getParameter("nome");
        String nickname = request.getParameter("nickname");
        String cpf = request.getParameter("cpf");
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        
        Usuario user = new Usuario(name, nickname, cpf, birth, phone, email, password, "client", true);
        //boolean b = ServiceUsuario.validateUser(user);
        dao = new DaoUsuario(user);
        dao.insert();
    }
}
