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
import java.util.List;
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

                Video video = getSingleVideo(rs);
                
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
            video = getSingleVideo(rs);
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

                Video video = getSingleVideo(rs);
                
                videos.add(video);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return videos;
        
    }
    
     /**
     * Gets a list of videos from the database, based on the words searched into a specific column.
     * If the search contains multiples words
     * @param search
     * @param column
     * @return 
     */
    public List<Video> searchVideos(String search, String column) {
        
        Connection connection = MySqlConnector.getInstance().getConnection();
        List<Video> videos = new ArrayList<>();
        String[] words = search.split(" ");
        String query = "Select * from videos WHERE";
        
        for (int i = 0; i < words.length; i++) {
            
            query += " POSITION(? in "+ column +") > 0";
            
            if (i < words.length - 1) {
                query += " AND";
            }
        }
       
        try {

          PreparedStatement pstmt = connection.prepareStatement(query);
        
          for (int i = 0; i < words.length; i++) {

              pstmt.setString(i+1, words[i]);
          }

          videos = processResult(pstmt);
          
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return videos;
    }
    
    public List<Video> searchVideos(int year) {
        
        Connection connection = MySqlConnector.getInstance().getConnection();
        List<Video> videos = new ArrayList<>();
        String query = "Select * from videos WHERE Year(release_date) = ?";
       
        try {

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, year);

            videos = processResult(pstmt);
          
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return videos;
    }
    
    private List<Video> processResult(PreparedStatement pstmt) throws SQLException {
        
        List<Video> videos = new ArrayList<Video>();
        
        ResultSet rs = pstmt.executeQuery();
            
        while(rs.next())
        {

            Video video = getSingleVideo(rs);
            videos.add(video);
        }
            
        return videos;
    }
    
    private Video getSingleVideo(ResultSet rs)
         throws SQLException {
        
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

        return video;
        
    }
}
