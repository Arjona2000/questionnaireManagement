package com.interview.questionnaireManagement.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratingQuestions")
// Esta clase define una pregunta de valoración
public class RatingQuestion {
	@Id
    private UUID id;
    private String title;
    private int minValue = 1;  // Valor por defecto
    private int maxValue = 5;  // Valor por defecto
    
    // Constructor
    public RatingQuestion(UUID id, String title, int minValue, int maxValue) {
        this.id = id;
        this.title = title;
        
        // Asegurarnos de que los valores están dentro de los límites
        if (minValue >= 1) {
            this.minValue = minValue;
        }
        
        if (maxValue <= 10) {
            this.maxValue = maxValue;
        }
    }
    
    // Constructor con valores por defecto
    public RatingQuestion(UUID id, String title) {
        this.id = id;
        this.title = title;
    }
    
    // Getters
    public UUID getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getMinValue() {
        return minValue;
    }
    
    public int getMaxValue() {
        return maxValue;
    }
    
    // Método para validar respuestas
    public boolean isValidAnswer(int answer) {
        if(answer < minValue || answer > maxValue) {
        	return false;
        }else return true;
    }
}