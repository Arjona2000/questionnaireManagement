package com.interview.questionnaireManagement.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questionnaireManagement.DTO.CreateMultiSelectQuestionDTO;
import com.interview.questionnaireManagement.Entities.MultiSelectQuestion;
import com.interview.questionnaireManagement.Repositories.MultiSelectQuestionRepository;

@Service
public class MultiSelectQuestionService {
    
    @Autowired
    private MultiSelectQuestionRepository repository;
    
    // Crea una nueva pregunta de selección múltiple
    public void createQuestion(UUID id, CreateMultiSelectQuestionDTO dto) {
        // Comprobar si ya existe una pregunta con ese ID
        if (repository.exists(id)) {
            throw new IllegalArgumentException("Ya existe una pregunta con ese ID");
        }
        
        // Comprobar que hay al menos 2 opciones
        if (dto.getOptions() == null || dto.getOptions().size() < 2) {
            throw new IllegalArgumentException("Las preguntas de selección múltiple deben tener al menos 2 opciones");
        }
        
        // Crear la pregunta
        MultiSelectQuestion question = new MultiSelectQuestion(id, dto.getTitle(), dto.getOptions());
        
        // Guardar la pregunta
        repository.save(question);
    }
    
    // Responde a una pregunta de selección múltiple

    public boolean answerQuestion(UUID id, List<String> selectedOptions) {
        // Buscar la pregunta
        MultiSelectQuestion question = repository.findById(id);
        
        // Si no existe, devolver false
        if (question == null) {
            return false;
        }
        
        // Validar la respuesta
        return question.isValidAnswer(selectedOptions);
    }
}