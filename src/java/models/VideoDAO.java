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
    
    
     public ArrayList<Video> findVideosByUserId(int userId) {

        ArrayList<Video> videos = new ArrayList<>();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos where user_id = " + userId  ;
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
            while (rs.next()) {

                Video video = new Video();

                video.setId(rs.getInt("ID"));
                video.setTitle(rs.getString("TITLE"));
                video.setAuthor(rs.getString("AUTHOR"));
                video.setDescription(rs.getString("DESCRIPTION"));
                video.setCreatedAt(rs.getDate("CREATED_AT"));                
                video.setReleaseDate(rs.getDate("RELEASE_DATE"));
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
     
    public Video saveVideo(Video video) {
             
        Connection connection = MySqlConnector.getInstance().getConnection();
        String sql = "INSERT INTO videos(TITLE,AUTHOR,CREATED_AT,RELEASE_DATE,DURATION,REPRODUCTIONS,DESCRIPTION,FORMAT,URL,USER_ID) "
                 + "VALUES (?,?,?,?,?,?,?,?,?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getAuthor());
            pstmt.setString(3, DateHelper.formatDate(video.getCreatedAt()));
            pstmt.setString(4, DateHelper.formatDate(video.getReleaseDate()));
            pstmt.setInt(5, video.getDuration());
            pstmt.setInt(6, 0);
            pstmt.setString(7, video.getDescription());
            pstmt.setString(8, video.getFormat());
            pstmt.setString(9, video.getUrl());             
            pstmt.setInt(10, video.getUserId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int lastInsertedId = rs.getInt(1);
                Video createdVideo = findVideoById(lastInsertedId);

                return createdVideo;
            }

            return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
         
    public Video findVideoById(int id) {

        Video video = new Video();
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
            video.setReleaseDate(rs.getDate("RELEASE_DATE"));
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

    public ArrayList<Video> getAll() {
        
        ArrayList<Video> videos = new ArrayList<>();
        Connection connection = MySqlConnector.getInstance().getConnection();
        
        String sql = "Select * from videos";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            
            while (rs.next()) {

                Video video = new Video();

                video.setId(rs.getInt("ID"));
                video.setTitle(rs.getString("TITLE"));
                video.setAuthor(rs.getString("AUTHOR"));
                video.setDescription(rs.getString("DESCRIPTION"));
                video.setCreatedAt(rs.getDate("CREATED_AT"));
                video.setReleaseDate(rs.getDate("RELEASE_DATE"));
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
