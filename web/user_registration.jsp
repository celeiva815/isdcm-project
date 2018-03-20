<%-- 
    Document   : user_registrarion
    Created on : Mar 14, 2018, 4:39:48 PM
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
        <script src="js/user.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
        <title>Registrar Usuario</title>
    </head>
    <body>
        
        <h1>Registrar nuevo Usuario</h1>
        
        <form name="register_user" onsubmit="return validateForm()" action="RegisterUser" method="POST">
            <p> Nombre de Usuario: </p>
            <input name="username"/>
            <p> Nombre: </p>
            <input name="name"/>
            <p> Apellidos: </p>
            <input name="lastnames"/>
            <p> Correo Electrónico: </p>
            <input name="email"/>
            <p> Contraseña: </p>
            <input name="password" type="password" id="password"/>            
            <p> Repetir contraseña: </p>
            <input name="confirm_password" type="password" id="confirm_password"/>
            <p></p>
            <input value="Registrar Usuario" type="submit" class="link"/>
            
            <p class="separator">
                ¿Ya eres usuario?
            </p>
            <a href="/VideoManager/login.jsp" class="link new-user">
                <span><i class="fas fa-user"></i></span>
                <span class="link-text">Iniciar Sesión</span>
            </a>
        </form>
    </body>
</html>
