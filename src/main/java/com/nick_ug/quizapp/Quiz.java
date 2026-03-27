package com.nick_ug.quizapp;

import java.util.*;

public class Quiz {
    private Long id;
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Quiz(Long id,String title){
        this.id = id;
        this.title = title;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public List<Question> getQuestions() {
        return questions;
    }

}
