package com.nick_ug.quizapp;

import io.swagger.v3.oas.annotations.media.Schema;

public class QuestionDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "What is JVM?")
    private String text;

    public QuestionDTO(Long id,String text){
        this.id = id;
        this.text = text;
    }

    public Long getId(){ return id; }

    public String getText(){return text; }

}
