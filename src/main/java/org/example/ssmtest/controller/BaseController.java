package org.example.ssmtest.controller;

import org.example.ssmtest.model.entity.Student;
import org.example.ssmtest.repository.StudentRepository;
import org.example.ssmtest.service.ComputeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BaseController {
    @Resource
    StudentRepository studentRepository;

    @Resource
    ComputeImpl compute;

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


    @GetMapping("compute")
    public ResponseEntity<?> compute(int a, int b, String operate){
        try{
            if (operate.equals("add")){
                return ResponseEntity.ok(compute.add(a, b));
            }
            if (operate.equals("sub")){
                return ResponseEntity.ok(compute.sub(a, b));
            }
            if (operate.equals("mul")){
                return ResponseEntity.ok(compute.mul(a, b));
            }
            if (operate.equals("div")){
                return ResponseEntity.ok(compute.div(a, b));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
