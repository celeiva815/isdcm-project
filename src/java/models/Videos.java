/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tito
 */
@Entity
@Table(name = "videos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Videos.findAll", query = "SELECT v FROM Videos v")
    , @NamedQuery(name = "Videos.findById", query = "SELECT v FROM Videos v WHERE v.id = :id")
    , @NamedQuery(name = "Videos.findByTitle", query = "SELECT v FROM Videos v WHERE v.title = :title")
    , @NamedQuery(name = "Videos.findByAuthor", query = "SELECT v FROM Videos v WHERE v.author = :author")
    , @NamedQuery(name = "Videos.findByDescription", query = "SELECT v FROM Videos v WHERE v.description = :description")
    , @NamedQuery(name = "Videos.findByCreatedAt", query = "SELECT v FROM Videos v WHERE v.createdAt = :createdAt")
    , @NamedQuery(name = "Videos.findByReproductions", query = "SELECT v FROM Videos v WHERE v.reproductions = :reproductions")
    , @NamedQuery(name = "Videos.findByDuration", query = "SELECT v FROM Videos v WHERE v.duration = :duration")
    , @NamedQuery(name = "Videos.findByFormat", query = "SELECT v FROM Videos v WHERE v.format = :format")
    , @NamedQuery(name = "Videos.findByUserId", query = "SELECT v FROM Videos v WHERE v.userId = :userId")
    , @NamedQuery(name = "Videos.findByVideoscol", query = "SELECT v FROM Videos v WHERE v.videoscol = :videoscol")})
public class Videos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "author")
    private String author;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "reproductions")
    private Integer reproductions;
    @Column(name = "duration")
    private Integer duration;
    @Size(max = 255)
    @Column(name = "format")
    private String format;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Size(max = 45)
    @Column(name = "videoscol")
    private String videoscol;

    public Videos() {
    }

    public Videos(Integer id) {
        this.id = id;
    }

    public Videos(Integer id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getReproductions() {
        return reproductions;
    }

    public void setReproductions(Integer reproductions) {
        this.reproductions = reproductions;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getUserId() {
        return userId;
    }
    
    public Users getUser() {
        return UserDAO.getInstance().findUserById(userId);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVideoscol() {
        return videoscol;
    }

    public void setVideoscol(String videoscol) {
        this.videoscol = videoscol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Videos)) {
            return false;
        }
        Videos other = (Videos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Videos[ id=" + id + " ]";
    }
    
}
