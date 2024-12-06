package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.*;
import com.example.learningmanagementsystem.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @Autowired
    private MaterialRepository MaterialRepository;

    @Autowired
    private AnnouncementRepository AnnouncementRepository;

    @GetMapping("/student")
    public String studentPage(Model model, Authentication authentication)
    {
        User user = UserRepository.findByEmail(authentication.getName());
        List<ClassRoom> classRooms = ClassRoomRepository.findAll();
        model.addAttribute("classRooms", classRooms);
        model.addAttribute("teacherName",user.getFirstname()+" "+user.getLastname());

        return "studentPart/sIndex";
    }





    @GetMapping("/student/showCourses")
    public String courses(Model model, Authentication authentication)
    {
        return "redirect:/student";
    }



    @PostMapping("/student/showCourses/enterCourse")
    public String showCourses(@RequestParam String joinCode, @RequestParam String ClassCode, Model model, Authentication authentication) {
        List<ClassRoom> classRooms = ClassRoomRepository.findAll();
        for (ClassRoom classRoom : classRooms) {
            System.out.println("ClassCode: " + classRoom.getClassCode() + ", CourseCode: " + classRoom.getCourseCode());
            if (classRoom.getClassCode().equals(joinCode) && classRoom.getCourseCode().equals(ClassCode)) {
                return "redirect:/sShowCourses/enterCourse?courseCode=" + classRoom.getCourseCode();
            }
        }

        System.out.println("No matching ClassRoom found for joinCode: " + joinCode + " and ClassCode: " + ClassCode);
        model.addAttribute("error", "Invalid Join Code or Class Code");
        return "studentPart/sIndex"; // Redirect to an error page or show an error message
    }




    @GetMapping("/sShowCourses/enterCourse")
    public String enterCourses(@RequestParam String courseCode, Model model, Authentication authentication) {

        model.addAttribute("topics", TopicRepository.findByCourseCode(courseCode));
        model.addAttribute("materials", MaterialRepository.findByCourseCodeOrderByDateDesc(courseCode));
        model.addAttribute("announcements", AnnouncementRepository.findByCourseCodeOrderByDateDesc(courseCode));
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

    @GetMapping("student/downloadMaterial")
    public ResponseEntity<Resource> downloadMaterial(@RequestParam String filename) {
        try {
            Path file = Paths.get("src/main/resources/static/uploads/" + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



}



