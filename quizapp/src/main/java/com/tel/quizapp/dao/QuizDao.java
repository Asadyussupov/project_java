package com.tel.quizapp.dao;

import com.tel.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository <Quiz,Integer>{
}
