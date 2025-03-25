package com.interview.questionnaireManagement.DTO;

import java.util.List;

/**
 * DTO para recibir respuestas a preguntas de selección múltiple
 */
public class MultiSelectAnswerDTO {
    
    private List<String> answers;
    
    // Constructor vacío necesario para deserialización JSON
    public MultiSelectAnswerDTO() {
    }
    
    // Constructor con todos los campos
    public MultiSelectAnswerDTO(List<String> answers) {
        this.answers = answers;
    }
    
    // Getters y setters
    public List<String> getAnswers() {
        return answers;
    }
    
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    
    @Override
    public String toString() {
        return "MultiSelectAnswerDTO{" +
                "answers=" + answers +
                '}';
    }
}