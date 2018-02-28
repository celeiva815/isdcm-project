/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Tito
 */
public class User {
   
    private int id;
    private String name;
    private String lastNames;
    private String email;
    private String username;
    private String passwordToken;
    
    public User(String name, String lastNames, String email, String username,
            String passwordToken) {
        
        this.name = name;
        this.lastNames = lastNames;
        this.email = email;
        this.username = username;
        this.passwordToken = passwordToken;
    }
    
    
    
    
}
