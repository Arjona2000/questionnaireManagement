package com.interview.questionnaireManagement.Repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.interview.questionnaireManagement.Entities.SingleSelectQuestion;

@Repository
public class SingleSelectQuestionRepository {
	// Usamos un HashMap para guardar las preguntas en memoria
	private Map<UUID, SingleSelectQuestion> questions = new HashMap<>();
	
	// Método para guardar una pregunta
	public void save(SingleSelectQuestion question) {
		questions.put(question.getId(), question);
	}
	
	// Buscar una pregunta por ID
	public SingleSelectQuestion findById(UUID id) {
		return questions.get(id);
	}
	
	// Comprobar si existe la pregunta
	public boolean exists(UUID id) {
		return questions.containsKey(id);
	}
	
}
