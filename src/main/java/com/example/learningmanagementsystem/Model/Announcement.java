package com.example.learningmanagementsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String date;
    private String courseCode;

    public Announcement() {
    }

    public Announcement(String title, String description, String date, String courseCode) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.courseCode = courseCode;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
