package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.Topics;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TopicRepository extends JpaRepository<Topics, Long> {

     List<Topics> findByCourseCode(String courseCode);
}
