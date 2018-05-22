/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import security.EncryptedVideoString;
import security.VideoEncrypter;

/**
 *
 * @author Tito
 */
@WebServlet(name = "EncryptVideo", urlPatterns = {"/user/EncryptVideo"})
@MultipartConfig
public class EncryptVideo extends HttpServlet {

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
        
        VideoEncrypter encrypter = VideoEncrypter.getInstance();

        //Obtenemos los parámetros del JSP
        String encryptation = request.getParameter("encryptation");        
        String password = request.getParameter("password");
        Part filePart = request.getPart("video");
        
        //obtenemos el contenido del archivo subido 
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        
        byte[] content = IOUtils.toByteArray(fileContent); 
        
        if (encryptation.equals("encrypt")) {
            
            byte[] videoEncrypted = encrypter.encryptBytes(content, password);
        
        try (OutputStream out = response.getOutputStream()) {
                out.write(videoEncrypted);
                out.flush();
            }
            
        } else {
            
            byte[] videoDecrypted = encrypter.desencryptBytes(content, password);
        
            try (OutputStream out = response.getOutputStream()) {
                out.write(videoDecrypted);
                out.flush();
            }   
        }
        
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
