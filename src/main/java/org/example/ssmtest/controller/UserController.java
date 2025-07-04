package org.example.ssmtest.controller;

import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
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
