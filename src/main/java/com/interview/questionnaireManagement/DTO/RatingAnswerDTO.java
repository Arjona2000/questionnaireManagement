package com.interview.questionnaireManagement.DTO;

public class RatingAnswerDTO {

	private int answer;

	// Constructor vacío necesario para deserialización JSON
	public RatingAnswerDTO() {
	}

	// Constructor con todos los campos
	public RatingAnswerDTO(int answer) {
		this.answer = answer;
	}

	// Getters y setters
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "RatingAnswerDto{" + "answer=" + answer + '}';
	}
}