package org.example.ssmtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class UserController {
    @GetMapping("")
    public String login() {

        return "hello";
    }
}
