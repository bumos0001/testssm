package org.example.ssmtest.repository;

import org.example.ssmtest.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> getStudentById(int id);
}
