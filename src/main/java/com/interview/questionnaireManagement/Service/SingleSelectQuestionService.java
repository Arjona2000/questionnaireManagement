package com.interview.questionnaireManagement.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questionnaireManagement.DTO.CreateSingleSelectQuestionDTO;
import com.interview.questionnaireManagement.Entities.SingleSelectQuestion;
import com.interview.questionnaireManagement.Repositories.SingleSelectQuestionRepository;

@Service
public class SingleSelectQuestionService {

	@Autowired
	private SingleSelectQuestionRepository repository;
	
	// Método para crear una nueva pregunta de selección única
	public void createQuestion(UUID id, CreateSingleSelectQuestionDTO dto) {
		// Comprobamos si ya existe una pregunta con ese ID
		if(repository.exists(id)) {
			throw new IllegalArgumentException("There is already a question linked to this ID");
		}
		
		// Comprobamos que hay al menos 2 opciones
		if(dto.getOptions() == null || dto.getOptions().size() < 2) {
			throw new IllegalArgumentException("Single-select questions must have at least 2 options");
		}
		
		// Creamos la pregunta
		SingleSelectQuestion question = new SingleSelectQuestion(id, dto.getTitle(), dto.getOptions());
		
		// Guardamos la pregunta
		repository.save(question);
	}
	
	// Método para responder a una pregunta de selección única
	public boolean answerQuestion(UUID id, String selectedOption) {
		// Buscamos la pregunta
		SingleSelectQuestion question = repository.findById(id);
		
		// Si no existe devolvemos false
		if(question == null) {
			return false;
		}
		
		// Validamos la respuesta
		return question.isValidAnswer(selectedOption);
	}
}
