package org.example.ssmtest.controller;

import org.example.ssmtest.model.entity.Student;
import org.example.ssmtest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class BaseController {
    @Resource
    StudentRepository studentRepository;

    @GetMapping("hello")
    public String hello(){
        Student byId = studentRepository.getById(1);
        return "hello, " + byId.getName() + "!";
    }
}
