package com.nick_ug.quizapp;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//@OneToMany
//    private List<Question> questions = new ArrayList<>();

    public Quiz(){}

    public Quiz(String title){
        this.title = title;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//    public List<Question> getQuestions() {
//        return questions;
//    }

}
