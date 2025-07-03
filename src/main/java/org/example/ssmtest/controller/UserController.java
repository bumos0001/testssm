package org.example.ssmtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("")
    public String index(Principal principal) {
        return principal.toString();
    }

    @PostMapping("user")
    public ResponseEntity<Object> register(@RequestBody User user) {
        boolean b = userService.insertUser(user);
        if (b) {
            return ResponseEntity.ok(null);
        }else{
            return ResponseEntity.status(400).body("插入失敗");
        }
    }
}
