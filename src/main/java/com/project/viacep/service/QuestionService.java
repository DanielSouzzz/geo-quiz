package com.project.viacep.service;

import com.project.viacep.model.Question;
import com.project.viacep.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getRandomQuestionsAndUnanswered(int userId) {
        var questions = questionRepository.findUnansweredQuestionsByUserId(userId);

        var random = new Random();

        for (int i = questions.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(questions, i, j);
        }

        return questions;
    }
}
