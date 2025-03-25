package com.interview.questionnaireManagement.DTO;

public class SingleSelectAnswerDTO {

	private String answer;

	// Constructor vacío necesario para la deserialización JSON
	public SingleSelectAnswerDTO() {

	}

	public SingleSelectAnswerDTO(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
