/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Videos.findByCreationDate", query = "SELECT v FROM Videos v WHERE v.creationDate = :creationDate")
    , @NamedQuery(name = "Videos.findByDuration", query = "SELECT v FROM Videos v WHERE v.duration = :duration")
    , @NamedQuery(name = "Videos.findByReproductions", query = "SELECT v FROM Videos v WHERE v.reproductions = :reproductions")
    , @NamedQuery(name = "Videos.findByDescription", query = "SELECT v FROM Videos v WHERE v.description = :description")
    , @NamedQuery(name = "Videos.findByFormat", query = "SELECT v FROM Videos v WHERE v.format = :format")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "TITLE", length = 255)
    private String title;
    @Size(max = 255)
    @Column(name = "AUTHOR", length = 255)
    private String author;
    @Size(max = 100)
    @Column(name = "CREATION_DATE", length = 100)
    private String creationDate;
    @Size(max = 100)
    @Column(name = "DURATION", length = 100)
    private String duration;
    @Column(name = "REPRODUCTIONS")
    private Integer reproductions;
    @Size(max = 255)
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @Size(max = 20)
    @Column(name = "FORMAT", length = 20)
    private String format;

    public Video() {
    }

    public Video(Integer id) {
        this.id = id;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getReproductions() {
        return reproductions;
    }

    public void setReproductions(Integer reproductions) {
        this.reproductions = reproductions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
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
