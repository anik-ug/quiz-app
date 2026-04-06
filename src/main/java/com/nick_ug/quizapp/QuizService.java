package com.nick_ug.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<QuizDTO> getAllQuizzes(Pageable pageable) {

        Page<Quiz> quizPage = quizRepository.findAll(pageable);

        return quizPage.map(quiz -> new QuizDTO(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getQuestions()
                        .stream()
                        .map(q -> new QuestionDTO(q.getId(),q.getText()))
                        .toList()
        ));
    }

    public List<QuizDTO> searchQuiz(String title){
        List<Quiz> quizzes = quizRepository.findByTitleContainingIgnoreCase(title);

        return quizzes.stream()
                .map(q->new QuizDTO(
                        q.getId(),
                        q.getTitle(),
                        q.getQuestions()
                                .stream()
                                .map(ques -> new QuestionDTO(ques.getId(), ques.getText()))
                                .toList()
                ))
                .toList();
    }


}
