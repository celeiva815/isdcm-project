<%-- 
    Document   : video_list
    Created on : Mar 14, 2018, 4:40:35 PM
    Author     : Tito
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="js/index.js"></script>
        
        <title>Lista de Videos</title>
    </head>
    <body>
        <h1>Lista de Videos</h1>
        <div>
            <table class="rwd-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Descripción</th>
                        <th>Fecha de Creación</th>
                        <th>Reproducciones</th>
                        <th>Duración</th>
                        <th>Formato</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${videos}" var="video">
                        <tr>
                            <td data-th="Id">${video.id}</td>
                            <td data-th="Título">${video.title}</td>
                            <td data-th="Autor">${video.author}</td>
                            <td data-th="Descripción">${video.description}</td>
                            <td data-th="Creado en">${video.createdAt}</td>
                            <td data-th="Reproducciones">${video.reproductions}</td>
                            <td data-th="Duración">${video.duration}</td>
                            <td data-th="Formato">${video.format}</td>
                            <td data-th="Usuario">${video.userId}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
