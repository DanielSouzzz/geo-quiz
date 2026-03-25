package com.project.viacep.controller;

import com.project.viacep.model.Question;
import com.project.viacep.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    @GetMapping("/unanswered/{userId}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(questionService.getRandomQuestionsAndUnanswered(userId));
    }
}
