package com.nick_ug.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Anik 🚀";
    }

}
