/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utils.MySqlConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;
import models.Users;
import utils.CookieHelper;

/**
 *
 * @author Tito
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {
    
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

    private Users logInUser(String username, String password) {
        
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from users where username = ?";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {

                Users user = new Users();
                
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastNames(rs.getString("lastnames"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        return null;
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
        
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        
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
        
        try {
 
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            boolean isRemembered = remember != null ? remember.equals("true") : false;  

            Users user = logInUser(username, password);
            
            if (user == null) {
                
                request.setAttribute("error", "El usuario ingresado no existe.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            
            } else if (!user.getPassword().equals(password)) {
                
                request.setAttribute("error", "La contraseña ingresada no es la correcta.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                
            } else {
                
                request.getSession().setAttribute("user", user);
                
                 if (isRemembered) {
                    String uuid = UUID.randomUUID().toString();
                    UserDAO.getInstance().saveUserUUID(user, uuid);
                     CookieHelper.addCookie(response, CookieHelper.COOKIE_NAME, uuid, CookieHelper.COOKIE_AGE);
                } else {
                    UserDAO.getInstance().deleteUserUUID(user);
                    CookieHelper.removeCookie(response, CookieHelper.COOKIE_NAME);
                }
                
                response.sendRedirect("/VideoManager/user/index.html");
            }
        }
        catch(Exception e) {
            
            request.setAttribute("error", "Hubo un error inesperado");
            doGet(request, response);
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
