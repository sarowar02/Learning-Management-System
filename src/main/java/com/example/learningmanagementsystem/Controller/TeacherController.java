package com.example.learningmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class TeacherController {


    @GetMapping("/teacher")
    private String teacherPage()
    {
        return "/teacher";
    }



    @GetMapping("/topicTime")
    private String topicTime()
    {
        return "/topicTime";
    }


    @GetMapping("/classRoom")
    private String classRoom()
    {
        return "/classRoom";
    }






}
