package org.example.ssmtest.controller;

import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.model.request.LoginRequest;
import org.example.ssmtest.service.UserService;
import org.example.ssmtest.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            User user = (User) userService.loadUserByUsername(loginRequest.getUsername());
            String s = jwtUtil.generateToken(user.getId().toString(), Collections.singletonList(user.getRoleId()));

            Map map = new HashMap();
            map.put("token", s);
            // 回傳 JWT 給前端
            return ResponseEntity.ok(map);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("帳號密碼錯誤");
        }


    }
}
