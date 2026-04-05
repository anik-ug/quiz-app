package com.nick_ug.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public QuizDTO getQuiz(Long id){
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));

        List<QuestionDTO> questionDTOs = quiz.getQuestions()
                .stream()
                .map(q -> new QuestionDTO(q.getId(),q.getText()))
                .toList();

        return new QuizDTO(quiz.getId(), quiz.getTitle(), questionDTOs);
    }

    public String submitQuiz(Long id, List<String> answers){
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));

        List<Question> questions = quiz.getQuestions();

        //Impo fix
        if(answers.size() != questions.size()){
            return "Invalid number of answers!";
        }

        int score = 0;

        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getAnswer().equalsIgnoreCase(answers.get(i))){
                score++;
            }
        }

        return "Score: " + score + "/" + questions.size();
    }

}
