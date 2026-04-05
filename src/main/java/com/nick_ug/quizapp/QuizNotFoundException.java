package com.nick_ug.quizapp;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message){
        super(message);
    }
}
