package com.nick_ug.quizapp;

public class Question {
    private Long id;
    private String questionText;
    private String answer;

    public Question(Long id, String questionText, String answer) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
    }
    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }
}
