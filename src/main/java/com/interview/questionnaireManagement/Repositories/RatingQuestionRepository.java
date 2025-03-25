package com.interview.questionnaireManagement.Repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.interview.questionnaireManagement.Entities.RatingQuestion;

@Repository	
public class RatingQuestionRepository {
	// Usamos un HashMap para guardar las preguntas en memoria
    private Map<UUID, RatingQuestion> questions = new HashMap<>();
    
    // Guardar una pregunta
    public void save(RatingQuestion question) {
        questions.put(question.getId(), question);
    }
    
    // Buscar una pregunta por ID
    public RatingQuestion findById(UUID id) {
        return questions.get(id);
    }
    
    // Comprobar si existe una pregunta
    public boolean exists(UUID id) {
        return questions.containsKey(id);
    }
}