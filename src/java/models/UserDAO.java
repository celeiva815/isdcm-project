/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utils.MySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tito
 */
public class UserDAO {
    
    public static UserDAO getInstance() {
        
        if (instance == null) {
            instance = new UserDAO();
        }
        
        return instance;
    }
    
    private static UserDAO instance;
    
    private UserDAO(){}
    
    public Users findUserById(int id) {
        
        Users user = new Users();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from users where id = " + id;
        
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
        while (rs.next()) {
            
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setLastNames(rs.getString("lastnames"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setSessionId(rs.getString("sessionid"));
        }
        
        return user;
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;

    }
    
    public Users findUserByUUID(String uuid) {
        
        Users user = new Users();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from users where sessionid = " + uuid;
        
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
        while (rs.next()) {
            
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setLastNames(rs.getString("lastnames"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setSessionId(rs.getString("sessionid"));
        }
        
        return user;
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
        
        
    }
    
     public Users saveUser(Users user) {
        
         Connection connection = MySqlConnector.getInstance().getConnection();
         String sql = "INSERT INTO users(username,name,lastnames,email,password) "
                 + "VALUES (?,?,?,?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getLastNames());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int lastInsertedId = rs.getInt(1);
                UserDAO userDAO = new UserDAO();   
                Users createdUser = userDAO.findUserById(lastInsertedId); 
                
                return createdUser;
            }
            
            return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
     
     public void saveUserUUID(Users user, String UUID) {
        
        String sql = "UPDATE users SET sessionid = ? WHERE id = ?";
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, UUID);
            pstmt.setInt(2, user.getId());

            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUserUUID(Users user) {
        
        saveUserUUID(user, "");
        
    }
}
