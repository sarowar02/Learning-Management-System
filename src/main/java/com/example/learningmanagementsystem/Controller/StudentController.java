package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.ClassRoom;
import com.example.learningmanagementsystem.Model.ClassRoutine;
import com.example.learningmanagementsystem.Model.Topics;
import com.example.learningmanagementsystem.Model.User;
import com.example.learningmanagementsystem.Repository.ClassRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.learningmanagementsystem.Repository.UserRepository;
import com.example.learningmanagementsystem.Repository.ClassRoutineRepository;
import com.example.learningmanagementsystem.Repository.TopicRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
class StudentController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ClassRoutineRepository ClassRoutineRepository;

    @Autowired
    private ClassRoomRepository ClassRoomRepository;
    @Autowired
    private TopicRepository TopicRepository;

    @GetMapping("/student")
    public String studentPage()
    {
        return "/student";
    }





    @GetMapping("/student/showCourses")
    public String courses(Model model, Authentication authentication)
    {
        User user = UserRepository.findByEmail(authentication.getName());
        List<ClassRoom> classRooms = ClassRoomRepository.findAll();
        model.addAttribute("classRooms", classRooms);
        model.addAttribute("teacherName",user.getFirstname()+" "+user.getLastname());
        return "studentPart/sShowCourses";
    }



    @PostMapping("/student/showCourses/enterCourse")
    public String showCourses(@RequestParam String joinCode, @RequestParam String ClassCode, Model model, Authentication authentication) {
        List<ClassRoom> classRooms = ClassRoomRepository.findAll();
        for (ClassRoom classRoom : classRooms) {
            System.out.println("ClassCode: " + classRoom.getClassCode() + ", CourseCode: " + classRoom.getCourseCode());
            if (classRoom.getClassCode().equals(joinCode) && classRoom.getCourseCode().equals(ClassCode)) {
                System.out.println("Matched ClassRoom: " + classRoom);
                model.addAttribute("classRoom", classRoom);
                return "studentPart/enterCourse"; // Proceed to the view
            }
        }

        System.out.println("No matching ClassRoom found for joinCode: " + joinCode + " and ClassCode: " + ClassCode);
        model.addAttribute("error", "Invalid Join Code or Class Code");
        return "studentPart/sIndex"; // Redirect to an error page or show an error message
    }




    @GetMapping("/sShowCourses/enterCourse")
    public String enterCourses(@RequestParam String courseCode, Model model, Authentication authentication) {

        List<Topics> topicsList = TopicRepository.findByCourseCode(courseCode);
        model.addAttribute("topics", topicsList);
        return "studentPart/enterCourse";
    }


    @GetMapping("/student/ShowRoutine")
    public String ShowRoutine(Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());

        // List<ClassRoutine> routines = ClassRoutineRepository.findByTeacherEmail(teacher.getEmail());
        List<ClassRoutine> ClassRoutine = ClassRoutineRepository.findAll();

        model.addAttribute("routines", ClassRoutine);
        return "studentPart/sShowRoutine";
    }



















}



