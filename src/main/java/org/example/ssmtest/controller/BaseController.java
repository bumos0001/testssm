package org.example.ssmtest.controller;

import org.example.ssmtest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {
    @Resource
    StudentRepository studentRepository;

    @GetMapping("hello")
    public String hello(){
        return "hello, andy!";
    }

    @GetMapping("goodMorning")
    public String goodMorning(){
        return "goodMorning, andy!";
    }

    @GetMapping("goodAfternoon")
    public String goodAfternoon(){
        return "goodAfternoon, andy!";
    }

    @GetMapping("goodEvening")
    public String goodEvening(){
        return "goodEvening, andy!";
    }

    @GetMapping("testJson")
    public ResponseEntity<?> testJson(){
        Map<String, Object> map = new HashMap<>();
        map.put("gender", "man");
        map.put("name", "andy");
        return ResponseEntity.ok(map);
    }

    @GetMapping("testImg")
    public ResponseEntity<?> testImg() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/home/andy/Desktop/test.jpg"));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
