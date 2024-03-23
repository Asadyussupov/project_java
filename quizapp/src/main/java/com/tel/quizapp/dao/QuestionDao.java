package com.tel.quizapp.dao;
import com.tel.quizapp.model.Question;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuestionDao extends JpaRepository<Question, Integer> {



   @Modifying
   @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ ", nativeQuery = true)
   List<Question> findRandomQuestionsByCategory(String category, int numQ);
   List<Question> findBydifficultylevel(String difficultlevel);
   List<Question> findByCategory(String category);
}
