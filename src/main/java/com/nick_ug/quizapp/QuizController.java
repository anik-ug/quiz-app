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
    public Quiz getQuiz(@PathVariable Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @PostMapping("/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId, @RequestBody Question question){
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) return "Quiz not found!";

        question.setQuiz(quiz);
        questionRepository.save(question);

        return "Question added";
    }


    @PostMapping("/{id}/submit")
    public String submitQuiz(@PathVariable Long id,
                             @RequestBody List<String> answers) {

        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) return "Quiz not found";

        int score = 0;
        List<Question> questions = quiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getAnswer().equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }

        return "Score: " + score + "/" + questions.size();
    }



}
