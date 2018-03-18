/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Users;

/**
 *
 * @author Tito
 */
@WebServlet(name = "RegisterUser", urlPatterns = {"/RegisterUser"})
public class RegisterUser extends HttpServlet {

    private Users createdUser;
    
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
        try {

            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String lastNames = request.getParameter("lastnames");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            Users user = createUser(username, name, lastNames, email, password);
            createdUser = addUser(user, MySqlConnector.getInstance().getConnection());
            
        }
         catch(Exception e) {
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            
        }
    }
    
    private Users createUser(String username, String name, String lastNames,
            String email, String password) {
        
        Users user = new Users();
        
        user.setUsername(username);
        user.setName(name);
        user.setLastNames(lastNames);
        user.setEmail(email);
        user.setPassword(password);
        
        return user;
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
        
        if (createdUser != null) {
            
            request.setAttribute("id", createdUser.getId());
            request.setAttribute("username", createdUser.getUsername());
            request.setAttribute("name", createdUser.getName());
            request.setAttribute("lastnames", createdUser.getLastNames());
            request.setAttribute("email", createdUser.getEmail());
            request.setAttribute("password", createdUser.getPassword());
            request.getRequestDispatcher("/user_view.jsp").forward(request, response);
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

    private Users addUser(Users user, Connection connection) {
        
         String sql = "INSERT INTO users(username,name,lastnames,email,password) "
                 + "VALUES (?,?,?,?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getLastNames());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int lastInsertedId = rs.getInt(1);
                Users createdUser = getUser(lastInsertedId); 
                
                return createdUser;
            }
            
            return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;

    }

    private Users getUser(int id) {
        
        Users user = new Users();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from users where id = " + id;
        
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
        while (rs.next()) {
            
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setLastNames(rs.getString("lastnames"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
        
        return user;
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

}
