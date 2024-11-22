package com.example.learningmanagementsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClassRoutine {

    @Id
    private String  courseCode;
    private String subject;
    private String teacherEmail;
    private String teacherName;
    private String room;
    private String day;
    private String semester;
    private String time;



    public ClassRoutine() {
    }

    public ClassRoutine(String course_code, String subject, String teacher_email, String teacher_name, String room, String day, String semester, String time) {
        this.courseCode = course_code;
        this.subject = subject;
        this.teacherEmail = teacher_email;
        this.teacherName = teacher_name;
        this.room = room;
        this.day = day;
        this.semester = semester;
        this.time = time;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public void settCourseCode(String courseCode) {
        this.courseCode= courseCode;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
