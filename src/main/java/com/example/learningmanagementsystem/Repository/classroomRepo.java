package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface classroomRepo extends JpaRepository<classroom, Long> {
  //  classroom findByEmail(String email);
}