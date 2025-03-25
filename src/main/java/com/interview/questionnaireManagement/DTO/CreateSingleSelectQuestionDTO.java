package com.interview.questionnaireManagement.DTO;

import java.util.List;

public class CreateSingleSelectQuestionDTO {
	private String title;
	private List<String> options;
	
	// Constructor vacío necesario para deserialización JSON
	public CreateSingleSelectQuestionDTO() {
		
	}

	public CreateSingleSelectQuestionDTO(String title, List<String> options) {
		this.title = title;
		this.options = options;
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
		this.options = options;
	}

}
