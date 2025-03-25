package com.interview.questionnaireManagement.DTO;

import java.util.List;

/**
 * DTO para crear preguntas de selección múltiple
 */
public class CreateMultiSelectQuestionDTO {
    
    private String title;
    private List<String> options;
    
    // Constructor vacío 
    public CreateMultiSelectQuestionDTO() {
    }
    
    // Constructor con todos los campos
    public CreateMultiSelectQuestionDTO(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }
    
    // Getters y setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    @Override
    public String toString() {
        return "CreateMultiSelectQuestionDTO{" +
                "title='" + title + '\'' +
                ", options=" + options +
                '}';
    }
}