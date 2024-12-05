package com.example.learningmanagementsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Materials {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String courseCode;
    private String date;
    private String file;

    public Materials() {
    }

    public Materials(String courseCode,String date, String file) {
        this.courseCode = courseCode;

        this.date = date;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }


    public String getDate() {
        return date;
    }

    public String getFile() {
        return file;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
