package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Materials, Long> {
    @Query("SELECT m FROM Materials m WHERE m.courseCode = :courseCode ORDER BY m.date DESC")
    List<Materials> findByCourseCodeOrderByDateDesc(@Param("courseCode") String courseCode);
}
