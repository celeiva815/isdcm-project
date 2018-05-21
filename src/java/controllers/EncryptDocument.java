/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import security.XMLEncrypter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Tito
 */
@WebServlet(name = "EncryptDocument", urlPatterns = {"/user/EncryptDocument"})
@MultipartConfig
public class EncryptDocument extends HttpServlet {

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
        
        //Inicializamos la herramienta de encriptación
        XMLEncrypter encrypter = XMLEncrypter.getInstance();
        
        //Obtenemos los parámetros del JSP
        String encryptation = request.getParameter("encryptation");
        String key = request.getParameter("key");
        Part filePart = request.getPart("document");
        
        //obtenemos el contenido del archivo subido 
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        String content = IOUtils.toString(fileContent); 
       
        //Creamos un documento en base al archivo subido
        Document document = createDocumentFromString(content);
                 
        try {
                
            if (encryptation.equals("encrypt")) {
               Document encryptedDoc = encrypter.encrypt(key, document, true);
               String encrypted = createStringFromDocument(encryptedDoc);

               
               try (PrintWriter out = response.getWriter()) {
                out.println(encrypted);
            } 
               
               
            } else {
               Document decryptedDoc = encrypter.decrypt(key, document);
               String decrypted = createStringFromDocument(decryptedDoc); 
               
               try (PrintWriter out = response.getWriter()) {
                out.println(decrypted);
                } 
            }
        } catch (Exception ex) {
            Logger.getLogger(EncryptDocument.class.getName()).log(Level.SEVERE, null, ex);
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                out.println(ex.getMessage());
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

    private Document createDocumentFromString(String xml){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xml.getBytes()));
            
        } catch (SAXException ex) {
            Logger.getLogger(EncryptDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EncryptDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EncryptDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private String createStringFromDocument(Document doc) {
    try {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    } catch (Exception ex) {
        return "Error converting to String";
    }
}
    
 
}
