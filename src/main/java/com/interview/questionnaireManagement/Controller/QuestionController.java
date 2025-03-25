
package com.interview.questionnaireManagement.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.questionnaireManagement.DTO.CreateMultiSelectQuestionDTO;
import com.interview.questionnaireManagement.DTO.CreateRatingQuestionDTO;
import com.interview.questionnaireManagement.DTO.CreateSingleSelectQuestionDTO;
import com.interview.questionnaireManagement.DTO.MultiSelectAnswerDTO;
import com.interview.questionnaireManagement.DTO.RatingAnswerDTO;
import com.interview.questionnaireManagement.DTO.SingleSelectAnswerDTO;
import com.interview.questionnaireManagement.Service.QuestionTypeFactory;
import com.interview.questionnaireManagement.Service.QuestionTypeFactory.QuestionType;

// Controlador unificado para todos los tipos de preguntas
@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionTypeFactory questionFactory;

	// Endpoint para crear una pregunta de valoración

	@PutMapping("/rating/{id}")
	public ResponseEntity<?> createRatingQuestion(@PathVariable UUID id, @RequestBody CreateRatingQuestionDTO dto) {
		boolean success = questionFactory.createQuestion(QuestionType.RATING, id, dto);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Pregunta de valoración creada correctamente");
		} else {
			return ResponseEntity.badRequest().body("Error al crear la pregunta de valoración");
		}
	}

	// Endpoint para responder a una pregunta de valoración
	@PostMapping("/rating/{id}")
	public ResponseEntity<?> answerRatingQuestion(@PathVariable UUID id, @RequestBody RatingAnswerDTO dto) {
		boolean valid = questionFactory.answerQuestion(QuestionType.RATING, id, dto);

		if (valid) {
			return ResponseEntity.ok("Respuesta válida");
		} else {
			return ResponseEntity.badRequest().body("Respuesta inválida o pregunta no encontrada");
		}
	}

	// Endpoint para crear una pregunta de selección múltiple

	@PutMapping("/multi-select/{id}")
	public ResponseEntity<?> createMultiSelectQuestion(@PathVariable UUID id,
			@RequestBody CreateMultiSelectQuestionDTO dto) {
		boolean success = questionFactory.createQuestion(QuestionType.MULTI_SELECT, id, dto);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Pregunta de selección múltiple creada correctamente");
		} else {
			return ResponseEntity.badRequest().body("Error al crear la pregunta de selección múltiple");
		}
	}

	// Endpoint para responder a una pregunta de selección múltiple

	@PostMapping("/multi-select/{id}")
	public ResponseEntity<?> answerMultiSelectQuestion(@PathVariable UUID id, @RequestBody MultiSelectAnswerDTO dto) {
		boolean valid = questionFactory.answerQuestion(QuestionType.MULTI_SELECT, id, dto);

		if (valid) {
			return ResponseEntity.ok("Respuesta válida");
		} else {
			return ResponseEntity.badRequest().body("Respuesta inválida o pregunta no encontrada");
		}
	}

	// Endpoint para crear una pregunta de selección única
	@PutMapping("/single-select/{id}")
	public ResponseEntity<?> createSingleSelectQuestion(@PathVariable UUID id,
			@RequestBody CreateSingleSelectQuestionDTO dto) {
		boolean success = questionFactory.createQuestion(QuestionType.SINGLE_SELECT, id, dto);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Pregunta de selección única creada correctamente");
		} else {
			return ResponseEntity.badRequest().body("Error al crear la pregunta de selección única");
		}
	}

	// Endpoint para responder a una pregunta de selección única
	@PostMapping("/single-select/{id}")
	public ResponseEntity<?> answerSingleSelectQuestion(@PathVariable UUID id, @RequestBody SingleSelectAnswerDTO dto) {
		boolean valid = questionFactory.answerQuestion(QuestionType.SINGLE_SELECT, id, dto);

		if (valid) {
			return ResponseEntity.ok("Respuesta válida");
		} else {
			return ResponseEntity.badRequest().body("Respuesta inválida o pregunta no encontrada");
		}
	}

	// Endpoint de prueba

	@GetMapping("/test")
	public String test() {
		return "API de preguntas funcionando";
	}
}
