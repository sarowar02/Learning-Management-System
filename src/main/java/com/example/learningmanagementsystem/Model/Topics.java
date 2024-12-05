package com.example.learningmanagementsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String topicName;
    String created_at;
    String courseCode;

    public Topics() {
    }

    public Topics(String topicName, String created_at, String course_code) {
        this.topicName = topicName;
        this.created_at = created_at;
        this.courseCode = course_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCourse_code() {
        return courseCode;
    }

    public void setCourse_code(String course_code) {
        this.courseCode = course_code;
    }
}
