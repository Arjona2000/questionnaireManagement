package com.interview.questionnaireManagement.DTO;

public class CreateRatingQuestionDTO {
    private String title;
    private Integer minValue;
    private Integer maxValue;
    
    // Constructor vacío 
    public CreateRatingQuestionDTO() {
    }
    
    // Constructor con todos los campos
    public CreateRatingQuestionDTO(String title, Integer minValue, Integer maxValue) {
        this.title = title;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    // Getters y setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getMinValue() {
        return minValue;
    }
    
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }
    
    public Integer getMaxValue() {
        return maxValue;
    }
    
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
    
    @Override
    public String toString() {
        return "CreateRatingQuestionDto{" +
                "title='" + title + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}