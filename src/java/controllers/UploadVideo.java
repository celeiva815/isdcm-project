/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utils.MySqlConnector;
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
import models.UserDAO;
import models.Users;
import models.VideoDAO;
import models.Videos;

/**
 *
 * @author Tito
 */
@WebServlet(name = "UploadVideo", urlPatterns = {"/user/UploadVideo"})
public class UploadVideo extends HttpServlet {
    
    
    
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
    
    public Videos createVideo(Users user, String title, String author, int duration, String description, String format) {
    
        Videos video = new Videos();
        
        video.setUserId(user.getId());
        video.setTitle(title);
        video.setAuthor(author);
        video.setDescription(description);
        video.setDuration(duration);
        video.setFormat(format);
        
        Date date = new Date();
        
        video.setCreatedAt(date);
        
        return video;
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
        
         try  {
           
            String title = request.getParameter("title");
            String author = request.getParameter("author");          
            int duration = Integer.parseInt(request.getParameter("duration"));
            String description = request.getParameter("description");
            String format = request.getParameter("format");
            Users user = (Users) request.getSession().getAttribute("user");
            
            Videos video = createVideo(user, title, author, duration, description, format);
            Videos createdVideo = VideoDAO.getInstance().saveVideo(video);
            
            if (createdVideo != null) {
        
                request.setAttribute("id", createdVideo.getId());
                request.setAttribute("title", createdVideo.getTitle());
                request.setAttribute("author", createdVideo.getAuthor());
                request.setAttribute("description", createdVideo.getDescription());
                request.setAttribute("createdat", createdVideo.getCreatedAt());
                request.setAttribute("reproductions", createdVideo.getReproductions());
                request.setAttribute("duration", createdVideo.getDuration());
                request.setAttribute("format", createdVideo.getFormat());
                request.setAttribute("userid", UserDAO.getInstance().findUserById(createdVideo.getUserId()).getUsername());
                request.getRequestDispatcher("/user/video_view.jsp").forward(request, response);
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
