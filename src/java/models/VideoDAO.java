/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DateHelper;
import utils.MySqlConnector;

/**
 *
 * @author Tito
 */
public class VideoDAO {
    
    private static VideoDAO instance;
    
    public static VideoDAO getInstance() {
        
        if (instance == null) {
            instance = new VideoDAO();
        }
        
        return instance;
    }
    
    private VideoDAO(){}
    
    
     public ArrayList<Videos> findVideosByUserId(int userId) {

        ArrayList<Videos> videos = new ArrayList<>();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos where user_id = " + userId  ;
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
            while (rs.next()) {

                Videos video = new Videos();

                video.setId(rs.getInt("ID"));
                video.setTitle(rs.getString("TITLE"));
                video.setAuthor(rs.getString("AUTHOR"));
                video.setDescription(rs.getString("DESCRIPTION"));
                video.setCreatedAt(rs.getDate("CREATED_AT"));
                video.setReproductions(rs.getInt("REPRODUCTIONS"));
                video.setDuration(rs.getInt("DURATION"));
                video.setFormat(rs.getString("FORMAT"));
                video.setUrl(rs.getString("URL"));
                video.setUserId(rs.getInt("USER_ID"));
                
                videos.add(video);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return videos;
    }
     
    public Videos saveVideo(Videos video) {
             
        Connection connection = MySqlConnector.getInstance().getConnection();
        String sql = "INSERT INTO videos(TITLE,AUTHOR,CREATED_AT,DURATION,REPRODUCTIONS,DESCRIPTION,FORMAT,URL,USER_ID) "
                 + "VALUES (?,?,?,?,?,?,?,?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getAuthor());
            pstmt.setString(3, DateHelper.formatDate(video.getCreatedAt()));
            pstmt.setInt(4, video.getDuration());
            pstmt.setInt(5, 0);
            pstmt.setString(6, video.getDescription());
            pstmt.setString(7, video.getFormat());
            pstmt.setString(8, video.getUrl());             
            pstmt.setInt(9, video.getUserId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int lastInsertedId = rs.getInt(1);
                Videos createdVideo = findVideoById(lastInsertedId);

                return createdVideo;
            }

            return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
         
    public Videos findVideoById(int id) {

        Videos video = new Videos();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos where id = " + id  ;
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
        while (rs.next()) {
            video.setId(rs.getInt("ID"));
            video.setTitle(rs.getString("TITLE"));
            video.setAuthor(rs.getString("AUTHOR"));
            video.setDescription(rs.getString("DESCRIPTION"));
            video.setCreatedAt(rs.getDate("CREATED_AT"));  
            video.setReproductions(rs.getInt("REPRODUCTIONS"));
            video.setDuration(rs.getInt("DURATION"));
            video.setFormat(rs.getString("FORMAT"));
            video.setUrl(rs.getString("URL"));
            video.setUserId(rs.getInt("USER_ID"));
        }
            
            return video;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public ArrayList<Videos> getAll() {
        
        ArrayList<Videos> videos = new ArrayList<>();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
            while (rs.next()) {

                Videos video = new Videos();

                video.setId(rs.getInt("ID"));
                video.setTitle(rs.getString("TITLE"));
                video.setAuthor(rs.getString("AUTHOR"));
                video.setDescription(rs.getString("DESCRIPTION"));
                video.setCreatedAt(rs.getDate("CREATED_AT"));
                video.setReproductions(rs.getInt("REPRODUCTIONS"));
                video.setDuration(rs.getInt("DURATION"));
                video.setFormat(rs.getString("FORMAT"));
                video.setUrl(rs.getString("URL"));
                video.setUserId(rs.getInt("USER_ID"));
                
                videos.add(video);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return videos;
        
    }
}
