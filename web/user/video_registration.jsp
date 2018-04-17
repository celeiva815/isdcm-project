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
        <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="../css/style.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="../js/index.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
        <title>Registrar video</title>
    </head>
    <body>
        <h1>Agrega un nuevo video</h1>
        
        <form name="register_video" action="UploadVideo" method="POST">
            
            <p>Título:</p>
            <input type="text" name="title"/>
            <p>Autor: </p>
            <input type="text" name="author"/>
            <p>Fecha de Estreno: </p>
            <input type="date" name="release_date"/>
            <p>Minutos de duración:</p>
            <input type="number" name="duration" min="1"/>
            <p>Descripción:</p>
            <input type="text" name="description"/>
            <p>Formato:</p>
            <input type="text" name="format"/>
            <p>URL:</p>
            <input type="text" name="url"/>
            <p></p>
            <input value="Subir Video" type="submit" class="link"/>
            <p class="login-error">${error}</p>
        </form>
    </body>
</html>
