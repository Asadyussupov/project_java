package com.tel.quizapp.service;

import com.tel.quizapp.model.Question;
import com.tel.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<List<Question>> getQuestionsByLevel(String difficultylevel) {
        return new ResponseEntity<>(questionDao.findBydifficultylevel(difficultylevel),HttpStatus.OK);
    }
    public ResponseEntity<String> addQuestion(Question question) {
        if(question!=null)
        {
            questionDao.save(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    public ResponseEntity<String> addQuestion(Question question) {
//        try{
//            questionDao.save(question);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("success",HttpStatus.CREATED);
//    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean existById(Integer id)
    {
        return questionDao.existsById(id);
    }
    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
        if(questionDao.existsById(id))
        {
            Question existQuestion = questionDao.getReferenceById(id);
            existQuestion.setCategory(question.getCategory());
            existQuestion.setDifficultylevel(question.getDifficultylevel());
            existQuestion.setOption1(question.getOption1());
            existQuestion.setOption2(question.getOption2());
            existQuestion.setOption3(question.getOption3());
            existQuestion.setOption4(question.getOption4());
            existQuestion.setQuestionTitle(question.getQuestionTitle());
            existQuestion.setRightAnswer(question.getRightAnswer());
            questionDao.save(existQuestion);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}