package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Repository.*;
import com.example.learningmanagementsystem.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstname,@RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password,@RequestParam String role) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String str = user.getRole();
            if(str.equals("ADMIN")){
                return "redirect:/admin";
            }
            else if(str.equals("TEACHER")){
                return "redirect:/teacher";
            }
            else if(str.equals("STUDENT")) {
                return "redirect:/student";
            }
            else System.out.println("Invalid Role");
        }
        else System.out.println("Invalid User");
        return "redirect:/login";
    }
}
