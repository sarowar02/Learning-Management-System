package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.ClassRoutine;
import com.example.learningmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoutineRepository extends JpaRepository<ClassRoutine, Long> {
    List<ClassRoutine> findByTeacherEmail(String email);
    ClassRoutine findByCourseCode(String courseCode);

}
