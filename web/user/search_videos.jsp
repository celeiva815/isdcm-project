<%-- 
    Document   : client
    Created on : Apr 11, 2018, 9:45:25 PM
    Author     : Tito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <title>Buscar Video</title>
    </head>
    <body>
        <form name="search_form" method="post" action="SearchVideos">
            <h1>Búsqueda de Videos</h1>
            <p>Título, Autor o Año</p>
            <input type="search" name ="search"/>
            <p>Tipo de búsqueda</p>
            <select name="search_types">
                <option value="title">Título</option>
                <option value="author">Autor</option>
                <option value="year">Año</option>
            </select>
            <p>
            <input type="submit" value="Buscar" name="search_button">
        </form>
        <p></p>
        <div class="separator">
            <a href="/VideoManager/user/index.html" class="link new-user">
                <span><i class="fas fa-home"></i></span>
                <span class="link-text">Volver al inicio</span>
            </a>
        </div>
    </body>
</html>
