package com.willis.quiz_service.dao;

import com.willis.Quiz.App.Demo.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
