package com.gruposet.ecommerce.servlets;

import com.gruposet.ecommerce.daos.DaoEndereco;
import com.gruposet.ecommerce.helpers.Messages;
import com.gruposet.ecommerce.models.Endereco;
import com.gruposet.ecommerce.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gruposet.ecommerce.daos.InterfaceDao;

public class ServletEndereco extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEndereco</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEndereco at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        if (request.getParameter("user_id").length() == 0) {
            String userId = request.getParameter("user_id");
            ArrayList<Endereco> enderecos = DaoEndereco.listAddress(Integer.valueOf(userId));
 
        } else if (request.getParameter("id").length() == 0) {
            String id = request.getParameter("id");
            Endereco endereco = new Endereco(Integer.valueOf(id));
            InterfaceDao db = new DaoEndereco(endereco);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            endereco.setPadrao(request.getParameter("padrao") == "1");
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
            endereco.setPadrao(request.getParameter("padrao") == "1");
            endereco.setAtivo(request.getParameter("ativo") == "1");
            endereco.setRua(request.getParameter("rua"));
            
            db.update();
        } else {
            Messages.writeError("Can't create or update Endereco");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
