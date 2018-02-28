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
public class Video {
    
    private int id;
    private String title;
    private String author;
    private String creationDate;
    private long duration;
    private int reproductions;
    private String description;
    private String format;
    private String source;
    private int userId;
    
    public Video(int id, String title, String author, String creationDate, 
            long duration, int reproductions, String description, String format, String source, int userId) {
    
        this.id = id;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.duration = duration;
        this.reproductions = reproductions;
        this.description = description;
        this.format = format;
        this.source = source;
        this.userId = userId;
    }
          
    
}
