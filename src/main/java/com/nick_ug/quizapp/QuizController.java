package com.nick_ug.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

//    create QUIZ
    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody Quiz quiz){
        return quizRepository.save(quiz);
    }

    //GET ALL QUIZ
    @GetMapping("/all")
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    //GET by ID
    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizRepository.findById(id).orElse(null);
    }



}
