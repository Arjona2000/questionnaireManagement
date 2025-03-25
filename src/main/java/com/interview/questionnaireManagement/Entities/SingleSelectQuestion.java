package com.interview.questionnaireManagement.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "singleSelectQuestion")
public class SingleSelectQuestion {
	@Id
	private UUID id;

	private String title;

	private List<String> options = new ArrayList<String>();

	public SingleSelectQuestion(UUID id, String title, List<String> options) {
		this.id = id;
		this.title = title;

		// Validamos que haya al menos 2 opciones
		if (!validOptions(options)) {
			throw new IllegalArgumentException("Single-select questions must have at least 2 options");
		}
		this.options = new ArrayList<>(options);
	}

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
		return options;
	}

	public void setOptions(List<String> options) {
		// Validamos que haya al menos 2 opciones
		if (!validOptions(options)) {
			throw new IllegalArgumentException("Single-select questions must have at least 2 options");
		}
		this.options = new ArrayList<>(options);
	}

	// Método para validar que al menos haya 2 opciones
	public boolean validOptions(List<String> options) {
		boolean result = true;
		if (options == null || options.size() < 2) {
			result = false;
		}
		return result;
	}

	// Método para validar respuestas
	public boolean isValidAnswer(String selectedOption) {
		// Verificamos que la opción seleccionada esté entre las opciones disponibles
		return this.options.contains(selectedOption);
	}
}
