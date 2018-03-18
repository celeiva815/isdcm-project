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
        <title>Inicio de sesión</title>
    </head>
    <body>

         <h1>Iniciar Sesión</h1>
        
        <form name="log_in" action="LogIn" method="POST">
            <p>Usuario: </p>
            <input name="username" value="tito"/>
            <p> Contraseña: </p>
            <input type="password" name="password" value="1234"/>            
            <p></p>
            <input value="Ingresar" type="submit"/>
        </form>
    </body>
</html>
