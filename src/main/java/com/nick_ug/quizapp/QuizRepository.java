package com.nick_ug.quizapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long>{
    List<Quiz> findByTitleContainingIgnoreCase(String title);
}
