<%-- 
    Document   : user_view
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
        <link rel="stylesheet" href="css/style.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="../js/index.js"></script>
        <script src="js/index.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
        <title>Usuario creado</title>
    </head>
    <body>
        <div>
            <h1>Usuario agregado correctamente!</h1>
            <h3>Id</h3>
            <p>${id}</p>
            <h3>Usuario</h3>
            <p>${username}</p>
            <h3>Nombre</h3>
            <p>${name}</p>
            <h3>Apellidos</h3>
            <p>${lastnames}</p>
            <h3>Correo Electrónico</h3>
            <p>${email}</p>
            <h3>Contraseña</h3>
            <p>${password}</p>
        </div>
        <div class="separator">
            <a href="/VideoManager/user/user_registration.jsp" class="link new-user">
                <span><i class="fas fa-user-plus"></i></span>
                <span class="link-text">Registrar nuevo usuario</span>
            </a>
            <a href="/VideoManager/login.jsp" class="link new-user">
                <span><i class="fas fa-user"></i></span>
                <span class="link-text">Iniciar Sesión</span>
            </a>
        </div>
        
    </body>
</html>
