package com.nick_ug.quizapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleQuizNotFound(QuizNotFoundException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("status", 404);
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
