package com.interview.questionnaireManagement.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "multiSelectQuestions")
public class MultiSelectQuestion {
    
    @Id
    private UUID id;
    
    private String title;
    
    @ElementCollection
    private List<String> options = new ArrayList<>();
    
    // Constructor
    public MultiSelectQuestion(UUID id, String title, List<String> options) {
        this.id = id;
        this.title = title;
        
        // Validamos que haya al menos 2 opciones
        if (options == null || options.size() < 2) {
            throw new IllegalArgumentException("Multi-select questions must have at least 2 options");
        }
        
        this.options = new ArrayList<>(options);
    }
    
    // Getters y setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getOptions() {
        return new ArrayList<>(options);
    }
    
    public void setOptions(List<String> options) {
        // Validamos que haya al menos 2 opciones
        if (options == null || options.size() < 2) {
            throw new IllegalArgumentException("Multi-select questions must have at least 2 options");
        }
        
        this.options = new ArrayList<>(options);
    }
    
    // Método para validar respuestas
    public boolean isValidAnswer(List<String> selectedOptions) {
        // Si no hay opciones seleccionadas, es válido (selección vacía)
        if (selectedOptions == null || selectedOptions.isEmpty()) {
            return true;
        }
        
        // Todas las opciones seleccionadas deben estar entre las opciones disponibles
        return this.options.containsAll(selectedOptions);
    }
}