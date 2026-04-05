package com.nick_ug.quizapp;

public class QuestionDTO {
    private Long id;
    private String text;

    public QuestionDTO(Long id,String text){
        this.id = id;
        this.text = text;
    }

    public Long getId(){ return id; }

    public String getText(){return text; }

}
