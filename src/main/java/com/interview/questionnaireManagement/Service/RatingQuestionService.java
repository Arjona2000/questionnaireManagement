package com.interview.questionnaireManagement.Service;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questionnaireManagement.DTO.CreateRatingQuestionDTO;
import com.interview.questionnaireManagement.Entities.RatingQuestion;
import com.interview.questionnaireManagement.Repositories.RatingQuestionRepository;

@Service
public class RatingQuestionService {

	@Autowired
	private RatingQuestionRepository repository;
	
	// Creamos una pregunta:
	
	public void createQuestion(UUID id, CreateRatingQuestionDTO dto) throws Exception {
		// Comprobamos si ya existe
		if(repository.exists(id)) {
			throw new Exception ("Ya existe una pregunta con ese ID");
		}
		
		RatingQuestion question;
		if(dto.getMinValue() != null && dto.getMaxValue() != null) {
			question = new RatingQuestion(id, dto.getTitle(), dto.getMinValue(), dto.getMaxValue());
		}else {
			question = new RatingQuestion(id, dto.getTitle());
		}
		repository.save(question);
	}
	
	public boolean answerQuestion(UUID id, int answer) throws Exception {
		// Buscamos la pregunta
		RatingQuestion question = repository.findById(id);
		
		// Si es vacía devolvemos una excepción
		if(question == null) {
			throw new Exception("No existe una pregunta con ese ID");
		}
		return question.isValidAnswer(answer);
	}
}
