/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import services.SearchVideoService_Service;

/**
 *
 * @author Tito
 */
@WebServlet(name = "SearchVideos", urlPatterns = {"/user/SearchVideos"})
public class SearchVideos extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SearchVideoService/SearchVideoService.wsdl")
    private SearchVideoService_Service service;

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

        String search = request.getParameter("search");
        String type = request.getParameter("search_types");
        List<services.Video> videos = new ArrayList<>();
        
        if (type.equals("title")) {
            
            videos = findVideoByTitle(search);
            
        } else if (type.equals("author")) {
        
            videos = findVideoByAuthor(search);

            
        } else if (type.equals("year")) {
            
            int year = Integer.parseInt(search);
            videos = findVideoByYear(year);
        } 
            
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/user/search_result.jsp").forward(request, response);
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

    private java.util.List<services.Video> findVideoByAuthor(java.lang.String author) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        services.SearchVideoService port = service.getSearchVideoServicePort();
        return port.findVideoByAuthor(author);
    }

    private java.util.List<services.Video> findVideoByTitle(java.lang.String title) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        services.SearchVideoService port = service.getSearchVideoServicePort();
        return port.findVideoByTitle(title);
    }

    private java.util.List<services.Video> findVideoByYear(int year) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        services.SearchVideoService port = service.getSearchVideoServicePort();
        return port.findVideoByYear(year);
    }

    
}
