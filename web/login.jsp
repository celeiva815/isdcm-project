<%-- 
    Document   : login
    Created on : Feb 28, 2018, 5:06:32 PM
    Author     : Tito
--%>

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
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
        <title>Inicio de sesión</title>
    </head>
    <body>

         <h1>Iniciar Sesión</h1>
        
        <form name="log_in" action="LogIn" method="POST">
            <p>Usuario: </p>
            <input name="username"/>
            <p> Contraseña: </p>
            <input type="password" name="password"/>
            <p></p>
            <label>Recordar usuario</label>
            <input class="text-checkbox" type="checkbox" name="remember" value="true" />
            <p></p>
            <input value="Ingresar" type="submit" class="link"/>
            
        </form>
        <p class="login-error">${error}</p>
        <p class="separator">
            ¿Nuevo usuario?
        </p>
        <a href="/VideoManager/user_registration.jsp" class="link">
            <span>
                <i class="fas fa-user-plus"></i>
            </span>
            <span class="link-text">Registrar Usuario</span>
        </a>
    </body>
</html>
