package com.example.learningmanagementsystem.Model;

import jakarta.persistence.*;

@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String CourseName;
    @Column(unique = true)
    private String CourseCode;

    @Column(unique = true)
    private String  ClassCode;

    private String  teacherEmail;


    public ClassRoom() {
    }

    public ClassRoom(String courseName, String courseCode, String classCode, String teacherEmail) {
        this.CourseName = courseName;
        this.CourseCode = courseCode;
        this.ClassCode = classCode;
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        ClassCode = classCode;
    }
}
