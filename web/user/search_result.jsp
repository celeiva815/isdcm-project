<%-- 
    Document   : video_list
    Created on : Mar 14, 2018, 4:40:35 PM
    Author     : Tito
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="css/style.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="../js/index.js"></script>
        <script src="js/index.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
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
                        <th>Estreno</th>
                        <th>Descripción</th>
                        <th>Reproducciones</th>
                        <th>Duración</th>
                        <th>Formato</th>
                        <th>Video</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${videos}" var="video">
                        <tr>
                            <td data-th="Id">${video.id}</td>
                            <td data-th="Título">${video.title}</td>
                            <td data-th="Autor">${video.author}</td>
                            <td data-th="Estreno"> <fmt:formatDate value="${video.releaseDate.toGregorianCalendar().time}" pattern="dd-MM-yyyy" />
                            </td>
                            <td data-th="Descripción">${video.description}</td>
                            <td data-th="Reproducciones">${video.reproductions}</td>
                            <td data-th="Duración">${video.duration}</td>
                            <td data-th="Formato">${video.format}</td>
                            <td data-th="Video">                    
                                <a href="${video.url}" class="link new-user" target="_blank">
                                    <c:if test="${not empty video.url}">
                                    <span><i class="fas fa-play"></i></span>
                                    </c:if>
                                </a>
                            </td>
                            <td data-th="Usuario">${video.getUserId()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
                <div class="separator">
            <ul>
                <li>
                    <a href="/VideoManager/user/search_videos.jsp" class="link new-user">
                        <span><i class="fas fa-upload"></i></span>
                        <span class="link-text">Buscar un nuevo video</span>
                    </a>
                </li>
                <li>
                    <a href="/VideoManager/user/index.html" class="link new-user">
                        <span><i class="fas fa-home"></i></span>
                        <span class="link-text">Volver al inicio</span>
                    </a>
                </li>
            </ul>
        </div>
    </body>
</html>
