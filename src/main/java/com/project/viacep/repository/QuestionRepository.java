package com.project.viacep.repository;

import com.project.viacep.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM questions q WHERE NOT EXISTS (SELECT 1 FROM user_answers u WHERE u.user_id = :userId AND u.question_id = q.id)", nativeQuery = true)
    List<Question> findUnansweredQuestionsByUserId(@Param("userId") int UserId);
}
