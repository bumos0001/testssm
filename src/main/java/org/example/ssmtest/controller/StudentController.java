package org.example.ssmtest.controller;

import org.example.ssmtest.mapper.StudentMapper;
import org.example.ssmtest.model.entity.Student;
import org.example.ssmtest.model.entity.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class StudentController {
    private final StudentMapper studentMapper;

    public StudentController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping("student/all")
    public ResponseEntity<?> getAllStudent(){
        List<Student> students = studentMapper.queryAllStudentAndAddress();
        return ResponseEntity.ok(students);
    }

    @GetMapping("teacher/all")
    public ResponseEntity<?> getAllTeacher(){
        List<Teacher> teachers = studentMapper.queryAllTeacherAndCourse();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("studentAndCourseAndTeacher")
    public ResponseEntity<?> getStudentAndCourseAndTeacher(){
        List<Student> students = studentMapper.queryAllStudentAndCourseAndTeacher();
        return ResponseEntity.ok(students);
    }
}
