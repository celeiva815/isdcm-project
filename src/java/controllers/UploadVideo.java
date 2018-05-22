/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.UserDAO;
import models.Users;
import models.VideoDAO;
import models.Video;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import security.VideoEncrypter;
import utils.DateHelper;

/**
 *
 * @author Tito
 */
@WebServlet(name = "UploadVideo", urlPatterns = {"/user/UploadVideo"})
@MultipartConfig
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
    
    public Video createVideo(Users user, String title, String author, Date releaseDate, int duration, String description, String format, String url) {
    
        Video video = new Video();
        
        video.setUserId(user.getId());
        video.setTitle(title);
        video.setAuthor(author);
        video.setReleaseDate(releaseDate);
        video.setDescription(description);
        video.setDuration(duration);
        video.setFormat(format);
        video.setUrl(url);
        
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
            Date releaseDate = DateHelper.parseDate(request.getParameter("release_date"), DateHelper.YEAR_MONTH_DAY);
            int duration = Integer.parseInt(request.getParameter("duration"));
            String description = request.getParameter("description");
            String format = request.getParameter("format");
            String url = "/VideoManager/videos/"+title+".mp4";
            Part file = request.getPart("video");
            boolean isEncrypted = request.getParameter("is_encrypted") != null;
            Users user = (Users) request.getSession().getAttribute("user");
            
            
            if (format.equals("mp4")) {
                saveVideo(file, title, user.getPassword(), isEncrypted);
            }
            
            Video video = createVideo(user, title, author, releaseDate, duration, description, format, url);
            Video createdVideo = VideoDAO.getInstance().saveVideo(video);
            
            if (createdVideo != null) {
        
                request.setAttribute("id", createdVideo.getId());
                request.setAttribute("title", createdVideo.getTitle());
                request.setAttribute("author", createdVideo.getAuthor());
                request.setAttribute("description", createdVideo.getDescription());
                request.setAttribute("createdat", createdVideo.getCreatedAt());                
                request.setAttribute("releasedate", createdVideo.getReleaseDate());
                request.setAttribute("reproductions", createdVideo.getReproductions());
                request.setAttribute("duration", createdVideo.getDuration());
                request.setAttribute("format", createdVideo.getFormat());
                request.setAttribute("url", createdVideo.getUrl());
                request.setAttribute("userid", UserDAO.getInstance().findUserById(createdVideo.getUserId()).getUsername());
                request.getRequestDispatcher("/user/video_view.jsp").forward(request, response);
                
            } else {
                request.setAttribute("error", "Ya existe un video registrado con ese título.");
                request.getRequestDispatcher("/user/video_registration.jsp").forward(request, response);
            }
        }
        catch(Exception e) {
             request.setAttribute("error", "Hubo un error al intentar registrar el video.");
             request.getRequestDispatcher("/user/video_registration.jsp").forward(request, response);
            
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

    
    private void saveVideo(Part multipart, String filename, String key, boolean isEncrypted) {
        
        String relativeWebPath = "/videos";
        String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
        File file = new File(absoluteFilePath, filename+".mp4");
        
        try {
            
            InputStream input = multipart.getInputStream();
            
            if (isEncrypted) {
                byte[] encrypted = VideoEncrypter.getInstance().encryptBytes(IOUtils.toByteArray(input), key);
                FileUtils.writeByteArrayToFile(file, encrypted);    
                
            } else {
                Files.copy(input, file.toPath());
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(UploadVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
} 
