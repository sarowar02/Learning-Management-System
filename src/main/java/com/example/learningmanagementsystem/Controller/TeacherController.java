package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.ClassRoom;
import com.example.learningmanagementsystem.Model.ClassRoutine;
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

import java.util.List;

@Controller
class TeacherController {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ClassRoutineRepository ClassRoutineRepository;

    @GetMapping("/teacher")
    public String teacherPage(Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<ClassRoutine> routines = ClassRoutineRepository.findByTeacherEmail(teacher.getEmail());
        model.addAttribute("routines", routines);
        return "teacher";
    }

    @GetMapping("/teacher/classRoutine")
    public String classRoutine(Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<ClassRoutine> routines = ClassRoutineRepository.findByTeacherEmail(teacher.getEmail());
        model.addAttribute("routines", routines);
        return "teacher";
    }
    @PostMapping("/teacher/add-routine")
    public String addRoutine(@RequestParam String course_code,@RequestParam String subject , @RequestParam String room, @RequestParam String day,@RequestParam String semester,@RequestParam String time, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        ClassRoutine routine = new ClassRoutine();
        routine.settCourseCode(course_code);
        routine.setSubject(subject);
        routine.setRoom(room);
        routine.setDay(day);
        routine.setSemester(semester);
        routine.setTime(time);
        routine.setTeacherEmail(teacher.getEmail());
        routine.setTeacherName(teacher.getFirstname()+" "+teacher.getLastname());
        ClassRoutineRepository.save(routine);
        return "redirect:/teacher/classRoutine";
    }

    @PostMapping("/teacher/classRoutine/update-routine")
    public String updateRoutine(@RequestParam String course_code,@RequestParam String subject , @RequestParam String room, @RequestParam String day,@RequestParam String semester,@RequestParam String time) {
        ClassRoutine routine = ClassRoutineRepository.findByCourseCode(course_code);
        routine.setSubject(subject);
        routine.setRoom(room);
        routine.setDay(day);
        routine.setSemester(semester);
        routine.setTime(time);
        ClassRoutineRepository.save(routine);
        return "redirect:/teacher/classRoutine";
    }



    @GetMapping("/teacher/classRoutine/delete-routine")
    @Transactional
    public String deleteRoutine(@RequestParam String id) {
        ClassRoutineRepository.deleteByCourseCode(id);
        return "redirect:/teacher/classRoutine";
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

@Controller
class ClassRoomController{
    @Autowired
    private ClassRoomRepository ClassRoomRepository;

    @Autowired
    private UserRepository UserRepository;
   @PostMapping("/teacher/create-classroom")
   public String CreateClassRoom(@RequestParam String CourseName, @RequestParam String CourseCode, @RequestParam String ClassCode,Authentication authentication,Model model)
    {
        ClassRoom classRoom = new ClassRoom(CourseName, CourseCode, ClassCode,authentication.getName());
        ClassRoomRepository.save(classRoom);

        model.addAttribute("classRoom", classRoom);
        return "redirect:/teacher/courses";
    }
    @GetMapping("/teacher/courses")
    public String courses(Model model,Authentication authentication)
    {
        User user = UserRepository.findByEmail(authentication.getName());
        List<ClassRoom> classRooms = ClassRoomRepository.findAll();
        model.addAttribute("classRooms", classRooms);
        model.addAttribute("teacherName",user.getFirstname()+" "+user.getLastname());
        return "classroom";
    }
}
