<%-- 
    Document   : encryptation
    Created on : May 18, 2018, 12:41:05 PM
    Author     : Tito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <title>Encriptación de XML</title>
    </head>
    <body>
        <form name="search_form" method="post" action="EncryptDocument" enctype="multipart/form-data">
            <h1>Encriptación de XMLs</h1>
            <p>Seleccione el archivo a encriptar o desencriptar:</p>
            <input type="file" name ="document"/>
            <p>Ingrese la contraseña:</p>
            <input type="password" name ="key" value="1234567891234567"/>
            <p></p>
            <input type="radio" name ="encryptation" value="encrypt"/>
            Encriptar <br/>
            <input type="radio" name ="encryptation" value="decrypt"/>
            Desencriptar <br/>
            <p>
            <input type="submit" value="Codificar" name="code">
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
