package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoUsuario;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.models.Usuario;
import com.gruposet.ecommerce.services.ServiceUsuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "ServletUsuario", urlPatterns = "usuario")
public class ServletUsuario extends HttpServlet {

    private InterfaceDao dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.dao = new DaoUsuario();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String res = "";
        Gson gson = new Gson();
        final String id = request.getParameter("id");
        if (id != null || id.length() != 0) {
            dao.select("id=" + id);
            res = gson.toJson(dao.get());
            System.out.println(res);
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
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());

        String nome = jObj.getString("nome");
        String apelido = jObj.getString("apelido");
        String cpf = jObj.getString("cpf");
        String datanasc = jObj.getString("datanasc");
        String telefone = jObj.getString("tel");
        String email = jObj.getString("email");
        String senha = jObj.getString("senha");

        System.out.println(nome + ", " + apelido + ", " + cpf + ", " + datanasc + ", " + telefone + ", " + email + ", " + senha);

        Usuario user;
        JSONObject error = new JSONObject();

        user = new Usuario(nome, apelido, cpf, datanasc, telefone, email, senha);
        this.dao.set(user);
        this.dao.insert();
    }
}
