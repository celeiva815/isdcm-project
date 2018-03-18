/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm() {
    
    var username = document.register_user.username.value;
    var email = document.register_user.email.value;
    var pass = document.register_user.password.value;
    var confirm = document.register_user.confirm_password.value;
    
    if (username == null || username == "") {
        alert ("El nombre de usuario no puede estar vacío.");
        return false;
    } else if (email == null || email == "") {
        alert ("El correo electrónico no puede estar vacío.");
        return false;
    } else if (!validateEmail(email)) {
        alert ("El correo electrónico no tiene el formato establecido.");
        return false;
    } else if (pass == null || pass == "") {
        alert ("La contraseña no puede estar vacía");
        return false;
    } else if (pass != confirm) {
        alert ("Las contraseñas no coinciden");
        return false;
    } else {
        return true;
    }
  }

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}