package org.example.ssmtest.controller;

import org.example.ssmtest.model.dto.StudentDTO;
import org.example.ssmtest.model.entity.Student;
import org.example.ssmtest.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentRepository studentRepository;

    @GetMapping("{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id){
        List<Student> studentById = studentRepository.getStudentById(id);
        if (studentById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentById);
    }

    @PostMapping("")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
        Student student = new Student();
        student.setName(studentDTO.getName());

        Student student1 = studentRepository.saveAndFlush(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO){
        List<Student> studentById = studentRepository.getStudentById(id);
        studentById.get(0).setName(studentDTO.getName());
        Student student = studentRepository.saveAndFlush(studentById.get(0));
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        List<Student> studentById = studentRepository.getStudentById(id);
        studentRepository.delete(studentById.get(0));
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAllStudent(){
        List<Student> studentById = studentRepository.findAll();
        return ResponseEntity.ok(studentById);
    }
}
