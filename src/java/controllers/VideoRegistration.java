/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Video;

/**
 *
 * @author Tito
 */
@WebServlet(name = "VideoRegistration", urlPatterns = {"/VideoRegistration"})
public class VideoRegistration extends HttpServlet {

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
           
            String title = request.getParameter("title");
            String author = request.getParameter("author");          
            String duration = request.getParameter("duration");
            String description = request.getParameter("description");
            String format = request.getParameter("format");
            
            out.println("llegó");
            
            createVideo(title, author, duration, description, format);
            
            out.println("Paso el create");
        }
        catch(Exception e) {
            
            out.println(e.getMessage());
            
        }
    }
    
    public void createVideo(String title, String author, String duration, String description, String format) {
    
        Video video = new Video();
        
        video.setTitle(title);
        video.setAuthor(author);
        video.setDescription(description);
        video.setDuration(duration);
        video.setFormat(format);
        
        Date date = new Date();
        
        video.setCreationDate(date.toString());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoManagerPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(video);
        em.getTransaction().commit();
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
