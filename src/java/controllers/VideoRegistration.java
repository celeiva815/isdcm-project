/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@WebServlet(name = "VideoRegistration", urlPatterns = {"/VideoRegistration"})
public class VideoRegistration extends HttpServlet {
    
    protected Videos createdVideo;
    
    
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
        
        try  {
           
            String title = request.getParameter("title");
            String author = request.getParameter("author");          
            int duration = Integer.parseInt(request.getParameter("duration"));
            String description = request.getParameter("description");
            String format = request.getParameter("format");
            
            Videos video = createVideo(title, author, duration, description, format);
            createdVideo = addVideo(video, MySqlConnector.getInstance().getConnection());
            
        }
        catch(Exception e) {
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            
        }
    }
    
    public Videos createVideo(String title, String author, int duration, String description, String format) {
    
        Videos video = new Videos();
        
        video.setUserId(1);
        video.setTitle(title);
        video.setAuthor(author);
        video.setDescription(description);
        video.setDuration(duration);
        video.setFormat(format);
        
        Date date = new Date();
        
        video.setCreatedAt(date);
        
        return video;
    }
       
    public Videos addVideo(Videos video, Connection connection) {
        
         String sql = "INSERT INTO videos(TITLE,AUTHOR,CREATED_AT,DURATION,REPRODUCTIONS,DESCRIPTION,FORMAT,USER_ID) "
                 + "VALUES (?,?,?,?,?,?,?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getAuthor());
            pstmt.setString(3, formatDate(video.getCreatedAt()));
            pstmt.setInt(4, video.getDuration());
            pstmt.setInt(5, 0);
            pstmt.setString(6, video.getDescription());
            pstmt.setString(7, video.getFormat());            
            pstmt.setInt(8, video.getUserId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int lastInsertedId = rs.getInt(1);
                Videos createdVideo = getVideo(lastInsertedId);

                return createdVideo;
            }

            return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    private String formatDate(Date date) {
        
        // Create an instance of SimpleDateFormat used for formatting 
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Using DateFormat format method we can create a string 
        // representation of a date with the defined format.
        return df.format(date);
    }
    
    public Videos getVideo(int id) {

        Videos video = new Videos();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos where id = " + id  ;
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
        while (rs.next()) {
            video.setId(rs.getInt("ID"));
            video.setTitle(rs.getString("TITLE"));
            video.setAuthor(rs.getString("AUTHOR"));
            video.setDescription(rs.getString("DESCRIPTION"));
            video.setCreatedAt(rs.getDate("CREATED_AT"));  
            video.setReproductions(rs.getInt("REPRODUCTIONS"));
            video.setDuration(rs.getInt("DURATION"));
            video.setFormat(rs.getString("FORMAT"));
            video.setUserId(rs.getInt("USER_ID"));
        }
            
            return video;
            
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
        
        if (createdVideo != null) {
        
            request.setAttribute("id", createdVideo.getId());
            request.setAttribute("title", createdVideo.getTitle());
            request.setAttribute("author", createdVideo.getAuthor());
            request.setAttribute("description", createdVideo.getDescription());
            request.setAttribute("createdat", createdVideo.getCreatedAt());
            request.setAttribute("reproductions", createdVideo.getReproductions());
            request.setAttribute("duration", createdVideo.getDuration());
            request.setAttribute("format", createdVideo.getFormat());
            request.setAttribute("userid", createdVideo.getUserId());
            request.getRequestDispatcher("/video_view.jsp").forward(request, response);
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
