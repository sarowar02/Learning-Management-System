package com.example.learningmanagementsystem.Repository;

import com.example.learningmanagementsystem.Model.ClassRoom;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository  extends JpaRepository<ClassRoom,Long> {
}
