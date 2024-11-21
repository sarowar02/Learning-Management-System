package com.example.learningmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AdminController {
    @GetMapping("/admin")
    private String adminPage()
    {
        return "/admin";
    }
}
