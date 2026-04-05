package com.nick_ug.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

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
    public QuizDTO getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId, @RequestBody Question question){
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));

        question.setQuiz(quiz);
        questionRepository.save(question);

        return "Question added";
    }


    @PostMapping("/{id}/submit")
    public String submitQuiz(@PathVariable Long id,
                             @RequestBody List<String> answers) {
        return quizService.submitQuiz(id, answers);
    }



}
