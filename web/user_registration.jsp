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
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Registro usuarios</h1>
        
        <form action="action">
            <p> Nombre de Usuario: </p>
            <input name="username"/>
            <p> Nombre: </p>
            <input name="name"/>
            <p> Apellidos: </p>
            <input name="last_names"/>
            <p> Correo Electrónico: </p>
            <input name="email"/>
            <p> Contraseña: </p>
            <input name="password"/>            
            <p> Repetir contraseña: </p>
            <input name="repeat_password"/>
            <p></p>
            <input value="Registrar" type="submit"/>
        </form>
    </body>
</html>
