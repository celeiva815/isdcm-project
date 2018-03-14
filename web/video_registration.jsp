<%-- 
    Document   : video_registration
    Created on : Mar 14, 2018, 4:40:19 PM
    Author     : Tito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agrega un nuevo video</h1>
        
        <form name="register_video" action="VideoRegistration" method="POST">
            
            <p>Título:</p>
            <input type="text" name="title"/>
            <p>Autor: </p>
            <input type="text" name="author"/>
            <p>Duración:</p>
            <input type="text" name="duration"/>
            <p>Descripción:</p>
            <input type="text" name="description"/>
            <p>Formato:</p>
            <input type="text" name="format"/>
            <br/>
            <input text="Registrar" type="submit"/>
        </form>
    </body>
</html>
