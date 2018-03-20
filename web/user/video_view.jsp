<%-- 
    Document   : video_view
    Created on : Mar 14, 2018, 5:03:49 PM
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
        <title>Video</title>
    </head>
    <body>
        <div>
            <h1>Video agregado correctamente!</h1>
            <h3>Id</h3>
            <p>${id}</p>
            <h3>Título</h3>
            <p>${title}</p>
            <h3>Autor</h3>
            <p>${author}</p>
            <h3>Descripción</h3>
            <p>${description}</p>
            <h3>Fecha de Creación</h3>
            <p>${createdat}</p>
            <h3>Reproducciones</h3>
            <p>${reproductions}</p>
            <h3>Duración</h3>
            <p>${duration}</p>
            <h3>Formato</h3>
            <p>${format}</p>
            <h3>Url</h3>
            <p>${url}</p>
            <h3>Usuario</h3>
            <p>${userid}</p>
        </div>
        <div class="separator">
            <ul>
                <li>
                    <a href="/VideoManager/user/video_registration.jsp" class="link new-user">
                        <span><i class="fas fa-upload"></i></span>
                        <span class="link-text">Registrar nuevo video</span>
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
