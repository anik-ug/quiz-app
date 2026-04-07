package com.nick_ug.quizapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank (message = "Question text cannot be empty")
    private String text;

    @JsonIgnore
    @NotBlank(message = "Answer cannot be empty")
    private String answer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private  Quiz quiz;

    public Question() {}

    public Question(String text, String answer,Quiz quiz) {
        this.text = text;
        this.answer = answer;
        this.quiz = quiz;
    }
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public Quiz getQuiz() { return quiz; }

    public void setQuiz(Quiz quiz) { this.quiz = quiz; }
}
