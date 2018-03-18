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
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Videos;

/**
 *
 * @author Tito
 */
@WebServlet(name = "ShowVideos", urlPatterns = {"/ShowVideos"})
public class ShowVideos extends HttpServlet {
    
         
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
        PrintWriter out = response.getWriter();
        
        try  {
           
      
        }
        catch(Exception e) {
            
            out.println(e.getMessage());
            
        }
    }
    
    public ArrayList<Videos> getVideos(int userId) {

        ArrayList<Videos> videos = new ArrayList<>();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos where user_id = " + userId  ;
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
            while (rs.next()) {

                Videos video = new Videos();

                video.setId(rs.getInt("ID"));
                video.setTitle(rs.getString("TITLE"));
                video.setAuthor(rs.getString("AUTHOR"));
                video.setDescription(rs.getString("DESCRIPTION"));
                video.setCreatedAt(rs.getDate("CREATED_AT"));
                video.setReproductions(rs.getInt("REPRODUCTIONS"));
                video.setDuration(rs.getInt("DURATION"));
                video.setFormat(rs.getString("FORMAT"));
                video.setUserId(rs.getInt("USER_ID"));
                
                videos.add(video);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return videos;
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
        
        ArrayList<Videos> videos = getVideos(1);
        
        if (videos.size() > 0) {
            
            request.setAttribute("videos", videos);
            request.getRequestDispatcher("/video_list.jsp").forward(request, response);
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
