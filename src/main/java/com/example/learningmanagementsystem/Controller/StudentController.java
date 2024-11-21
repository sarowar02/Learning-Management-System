package com.example.learningmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class StudentController {

    @GetMapping("/student")
    public String studentPage()
    {
        return "/student";
    }
}
