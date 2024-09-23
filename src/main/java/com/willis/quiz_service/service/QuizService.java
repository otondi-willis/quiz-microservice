package com.willis.quiz_service.service;


import com.willis.quiz_service.dao.QuizDao;
import com.willis.quiz_service.feign.QuizInterface;
import com.willis.quiz_service.model.QuestionWrapper;
import com.willis.quiz_service.model.Quiz;
import com.willis.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        //      List<Integer> questions = //call the generate url - RestTemplate http://localhost:8080/question/generate
//
//        // Ensure we only return up to numQ questions
//        if (questions.size() < numQ) {
//            return new ResponseEntity<>("Not enough questions in the selected category", HttpStatus.BAD_REQUEST);
//        }
//        // Limiting the questions to numQ
//        List<Question> limitedQuestions = questions.subList(0, numQ);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(limitedQuestions);
//        quizDao.save(quiz);




        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);


        return questions;


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
