package com.gruposet.ecommerce.servlets;

import com.google.gson.Gson;
import com.gruposet.ecommerce.daos.DaoProduto;
import com.gruposet.ecommerce.daos.DaoSession;
import com.gruposet.ecommerce.daos.DaoUsuario;
import com.gruposet.ecommerce.daos.InterfaceDao;
import com.gruposet.ecommerce.helpers.Messages;
import com.gruposet.ecommerce.models.Produto;
import com.gruposet.ecommerce.models.UserSession;
import com.gruposet.ecommerce.models.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "ServletProduto", urlPatterns = "produto")
public class ServletSession extends HttpServlet {

    private InterfaceDao dao;
    private String res;
    private Gson gson;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        this.dao = new DaoSession();
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
            System.out.println("Search products");
            if (request.getParameter("search") == null || request.getParameter("search").isEmpty()) {
                System.out.println("Search products without parameters.");
                dao.list("1=1");
            } else {
                String p = request.getParameter("search");
                System.out.println("Search with parameters." + p);
                dao.list("LOWER(modelo) LIKE LOWER('%" + p + "%')"
                        + " OR LOWER(marca) LIKE LOWER('%" + p + "%')"
                        + " OR LOWER(descricao) LIKE LOWER('%" + p + "%')");
            }

            res = gson.toJson(dao.getList());
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(res);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        dao = new DaoSession();
        BufferedReader payloadRequest = request.getReader();
        JSONObject json = new JSONObject(payloadRequest.readLine());
        
        if (request.getParameter("delete") != null) {
            if (json.has("id_usuario")) {
                dao.select("id_usuario=" + json.getInt("id_usuario"));
                dao.delete();
                try (PrintWriter out = response.getWriter()) {
                    out.print("");
                    out.flush();
                }
            }
        } else if (request.getParameter("get") != null) {
            if (json.has("id_usuario") && json.has("hash")) {
                dao.select("hash='" + json.getString("hash") + "' AND id_usuario=" +
                        json.getInt("id_usuario"));
                try (PrintWriter out = response.getWriter()) {
                    if ("[]".equals(gson.toJson(dao.get()))) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.print("");
                        out.flush();
                    } else {
                        response.setStatus(HttpServletResponse.SC_ACCEPTED);
                        out.print(gson.toJson(dao.get()));
                        out.flush();
                    }
                }
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                if (json.has("email") && json.has("senha")) {
                    final InterfaceDao daoUsuario = new DaoUsuario();
                    daoUsuario.select("email='"
                            + json.getString("email") + "' AND senha='"
                            + json.getString("senha") + "'");
                    System.out.println("dados");

                    Usuario u = (Usuario) daoUsuario.get();
                    try {
                        System.out.println("id=" + u.getId() + " email=" + u.getEmail() + " senha=" + u.getSenha() + " " + u.isAtivo());
                        if (u.isAtivo()) {
                            String hash = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                            System.out.println("Print=" + hash);
                            if (hash.isEmpty()) {
                                System.out.println("Hash is null");
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                out.print("");
                                out.flush();
                            }

                            if (u.getId() <= 0) {
                                System.out.println("Id is null " + u.getId());
                            }

                            UserSession s = new UserSession();
                            s.setId_usuario(u.getId());
                            s.setHash(hash);

                            dao.set(s);
                            dao.insert();
                            res = gson.toJson(dao.get());

                            response.setStatus(HttpServletResponse.SC_CREATED);
                            out.print(res);
                            out.flush();
                        } else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            out.print("");
                            out.flush();
                        }
                    } catch (Exception e) {
                        Messages.writeError("Error on get id of user");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.print("");
                        out.flush();
                    }
                } else {
                    out.print(res);
                    out.flush();
                }

            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public static void updateEstoqueProduto(int id, int quantidade) {
        System.out.println("Update estoque " + id);
        InterfaceDao d = new DaoProduto();
        d.select("id=" + id);
        Produto p = (Produto) d.get();
        p.setEstoque((p.getEstoque() - quantidade));
        d.set(p);
        d.update();
    }
}
