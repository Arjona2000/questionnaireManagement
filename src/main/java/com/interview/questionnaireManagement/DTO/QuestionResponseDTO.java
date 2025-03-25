package com.interview.questionnaireManagement.DTO;

/**
 * DTO para devolver información sobre una pregunta y su rango de valores
 */
public class QuestionResponseDTO {
    private long id;
    private String title;
    private int minValue;
    private int maxValue;
    
    // Constructor vacío
    public QuestionResponseDTO() {
    }
    
    // Constructor con todos los campos
    public QuestionResponseDTO(long id, String title, int minValue, int maxValue) {
        this.id = id;
        this.title = title;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    // Getters y setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getMinValue() {
        return minValue;
    }
    
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    
    public int getMaxValue() {
        return maxValue;
    }
    
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    } 
}