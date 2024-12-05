package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.*;
import com.example.learningmanagementsystem.Repository.*;
import jakarta.transaction.Transactional;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
class TeacherController {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ClassRoutineRepository ClassRoutineRepository;

    @Autowired
    private AnnouncementRepository AnnouncementRepository;

    @Autowired
    private MaterialRepository MaterialRepository;

    @GetMapping("/teacher")
    public String teacherPage(Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<ClassRoutine> routines = ClassRoutineRepository.findByTeacherEmail(teacher.getEmail());
        model.addAttribute("routines", routines);
        model.addAttribute("teacherName", teacher.getFirstname()+ " " + teacher.getLastname());
        return "teacher";
    }

    @GetMapping("/teacher/classRoutine")
    public String classRoutine(Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<ClassRoutine> routines = ClassRoutineRepository.findByTeacherEmail(teacher.getEmail());
        model.addAttribute("routines", routines);
        return "classroutine";
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
    public String updateRoutine(@RequestParam Long id,@RequestParam String course_code,@RequestParam String subject , @RequestParam String room, @RequestParam String day,@RequestParam String semester,@RequestParam String time) {
        ClassRoutine routine = ClassRoutineRepository.findById(id).get();
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
    public String deleteRoutine(@RequestParam Long id) {
        ClassRoutineRepository.deleteById(id);
        return "redirect:/teacher/classRoutine";
    }

    @GetMapping("/teacher/courses/class")
    public String classPage(@RequestParam String id, Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<Announcement> announcements = AnnouncementRepository.findByCourseCodeOrderByDateDesc(id);
        List<Materials> materials = MaterialRepository.findByCourseCodeOrderByDateDesc(id);
        model.addAttribute("courseId", id);
        model.addAttribute("announcements", announcements);
        model.addAttribute("materials", materials);

        return "classroom";
    }


}

@Controller
class ClassRoomController{
    @Autowired
    private ClassRoomRepository ClassRoomRepository;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private TopicRepository TopicRepository;

   @Autowired
   private AnnouncementRepository AnnouncementRepository;

   @Autowired
   private MaterialRepository MaterialRepository;
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
        return "courses";
    }

    @GetMapping("/classroom/topicList")
    public String topicList(@RequestParam String id, Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        List<Topics> topics = TopicRepository.findByCourseCode(id);
        model.addAttribute("topics", topics);
        model.addAttribute("user", teacher);
        model.addAttribute("courseId", id);
        return "topicList";

    }
    @PostMapping("/teacher/courses/classroom/addTopic")
    public String addTopic(@RequestParam String topicName,@RequestParam String id,Model model, Authentication authentication) {
        User teacher = UserRepository.findByEmail(authentication.getName());
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String date = currentDate.format(formatter);
        Topics topic = new Topics(topicName,date,id);
        TopicRepository.save(topic);
        return "redirect:/classroom/topicList?id="+id;

    }
    @GetMapping("/teacher/courses/classroom/deleteTopic")
    public String deleteTopic(@RequestParam Long id) {
        Topics topic = TopicRepository.findById(id).get();
        String courseId = topic.getCourse_code();
        TopicRepository.deleteById(id);
        return "redirect:/classroom/topicList?id="+courseId;

    }

    @PostMapping("teacher/courses/classroom/addAnnouncement")
    public String addAnnouncement(@RequestParam String title, @RequestParam String desc, @RequestParam String courseCode) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
        Announcement announcement = new Announcement(title, desc, date, courseCode);
        AnnouncementRepository.save(announcement);
        return "redirect:/teacher/courses/class?id="+courseCode;
    }
    @PostMapping("/teacher/courses/classroom/updateAnnouncement")
    public String updateAnnouncement(@RequestParam Long id, @RequestParam String updatetitle, @RequestParam String updatedesc) {
       String courseCode = AnnouncementRepository.findById(id).get().getCourseCode();
        Announcement announcement = AnnouncementRepository.findById(id).get();
        announcement.setTitle(updatetitle);
        announcement.setDescription(updatedesc);
        AnnouncementRepository.save(announcement);
        return "redirect:/teacher/courses/class?id="+courseCode;
    }
    @GetMapping("/teacher/courses/classroom/deleteAnnouncement")
    public String deleteAnnouncement(@RequestParam Long id) {
        String courseCode = AnnouncementRepository.findById(id).get().getCourseCode();
        AnnouncementRepository.deleteById(id);
        return "redirect:/teacher/courses/class?id="+courseCode;
    }

    @PostMapping("/teacher/courses/classroom/addMaterial")
    public String addMaterial(@RequestParam("file") MultipartFile file, @RequestParam String courseCode) {
        if (file.isEmpty()) {
            // Handle empty file case
            return "redirect:/teacher/courses/class?id=" + courseCode;
        }

        try {
            // Save the file to a static resource directory
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Save the filename in the database
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = currentDate.format(formatter);
            Materials material = new Materials(courseCode, date, fileName);
            MaterialRepository.save(material);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/teacher/courses/class?id=" + courseCode;
    }

    @GetMapping("/teacher/courses/classroom/deleteMaterial")
    public String deleteMaterial(@RequestParam Long id)
            {
                String courseCode = MaterialRepository.findById(id).get().getCourseCode();
               MaterialRepository.deleteById(id);

               return "redirect:/teacher/courses/class?id="+courseCode;
            }
}
