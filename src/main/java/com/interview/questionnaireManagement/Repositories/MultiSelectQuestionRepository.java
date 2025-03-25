package com.interview.questionnaireManagement.Repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.interview.questionnaireManagement.Entities.MultiSelectQuestion;

@Repository
public class MultiSelectQuestionRepository {
    
    // Usamos un HashMap para guardar las preguntas en memoria
    private Map<UUID, MultiSelectQuestion> questions = new HashMap<>();
    
    // Guardar una pregunta
    public void save(MultiSelectQuestion question) {
        questions.put(question.getId(), question);
    }
    
    // Buscar una pregunta por ID
    public MultiSelectQuestion findById(UUID id) {
        return questions.get(id);
    }
    
    // Comprobar si existe una pregunta
    public boolean exists(UUID id) {
        return questions.containsKey(id);
    }
}