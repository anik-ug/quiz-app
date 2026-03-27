package com.nick_ug.quizapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private List<Quiz> quizList = new ArrayList<>();
    private Long idCounter = 1L;
    private Long questionIdCounter = 1L;

    @GetMapping("/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId,
                              @RequestParam String text,
                              @RequestParam String answer) {

        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {

                Question q = new Question(questionIdCounter++, text, answer);
                quiz.getQuestions().add(q);

                return "Question added!";
            }
        }

        return "Quiz not found!";
    }
    @GetMapping("/{quizId}/questions")
    public List<Question> getQuestions(@PathVariable Long quizId) {

        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {
                return quiz.getQuestions();
            }
        }

        return new ArrayList<>();
    }
    @PostMapping("/create")
    public Quiz createQuiz(@RequestParam String title){

        Quiz quiz = new Quiz(idCounter++,title);
        quizList.add(quiz);

        return quiz;
    }
    @GetMapping("/all")
    public List<Quiz> getAllQuizzes() {
        return quizList;
    }

}
