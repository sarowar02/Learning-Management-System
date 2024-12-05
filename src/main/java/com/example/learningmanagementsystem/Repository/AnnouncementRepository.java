package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("SELECT a FROM Announcement a WHERE a.courseCode = :courseCode ORDER BY a.date DESC")
    List<Announcement> findByCourseCodeOrderByDateDesc(@Param("courseCode") String courseCode);
}
