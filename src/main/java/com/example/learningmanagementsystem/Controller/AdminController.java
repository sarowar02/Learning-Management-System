package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.learningmanagementsystem.Model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class AdminController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/admin")
    private String adminPage(Model m)
    {
        List<User> teachers = userRepository.findByRole("ROLE_TEACHER");
        List<User> students = userRepository.findByRole("ROLE_STUDENT");
        m.addAttribute("teachers",teachers);
        m.addAttribute("students",students);
        return "admin";
    }

    @PostMapping("/admin/teacher/update")
    public String updateTeacher(@RequestParam Long id,@RequestParam String teacher_first_name,@RequestParam String teacher_last_name,@RequestParam String teacher_email)
    {

        User teacher =  userRepository.findById(id).get();

        teacher.setFirstname(teacher_first_name);
        teacher.setLastname(teacher_last_name);
        teacher.setEmail(teacher_email);

        userRepository.save(teacher);

        return "redirect:/admin";
    }

    @GetMapping("/admin/teacher/delete")
    public String deleteTeacher(@RequestParam Long id)
    {
        userRepository.deleteById(id);

        return "redirect:/admin";
    }


    @PostMapping("admin/student/update")
    public String studentTeacher(@RequestParam Long id,@RequestParam String student_first_name,@RequestParam String student_last_name,@RequestParam String student_email)
    {

        User student =  userRepository.findById(id).get();

        student.setFirstname(student_first_name);
        student.setLastname(student_last_name);
        student.setEmail(student_email);

        userRepository.save(student);

        return "redirect:/admin";
    }

    @GetMapping("/admin/student/delete")
    public String deleteStudent(@RequestParam Long id)
    {
        userRepository.deleteById(id);

        return "redirect:/admin";
    }


}
