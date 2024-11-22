package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Repository.classroomRepo;
import com.example.learningmanagementsystem.Model.classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class classroomController {

    @Autowired
    private classroomRepo classroomRepo;

    @GetMapping("/classroom")
    public String classroom() {
        return "classRoom";
    }

    @PostMapping("/classroom")
    public String createClassroom(@ModelAttribute classroom classroom) {  // Changed parameter type to classroom model
        classroomRepo.save(classroom);
        return "redirect:/classroom";
    }
}