/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;
import models.Users;

/**
 *
 * @author Tito
 */
@WebServlet(name = "RegisterUser", urlPatterns = {"/RegisterUser"})
public class RegisterUser extends HttpServlet {

    
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
       
    }
    
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
        
         try {

            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String lastNames = request.getParameter("lastnames");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            Users user = new Users(username, name, lastNames, email, password);
            Users createdUser = UserDAO.getInstance().saveUser(user);
            
            if (createdUser != null) {
            
                request.setAttribute("id", createdUser.getId());
                request.setAttribute("username", createdUser.getUsername());
                request.setAttribute("name", createdUser.getName());
                request.setAttribute("lastnames", createdUser.getLastNames());
                request.setAttribute("email", createdUser.getEmail());
                request.setAttribute("password", createdUser.getPassword());
                request.getRequestDispatcher("/user/user_view.jsp").forward(request, response);
            }
        }
         catch(Exception e) {
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
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
