package com.nick_ug.quizapp;

import java.util.List;

public class QuizDTO {

    private Long id;
    private String title;
    private List<QuestionDTO> questions;

    public QuizDTO(Long id,String title,List<QuestionDTO> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public List<QuestionDTO> getQuestions() { return questions; }

}
