package com.interview.questionnaireManagement.DTO;

public class ApiResponseDTO<T> {

	private boolean success;
	private String message;
	private T data;

	// Constructor vacío
	public ApiResponseDTO() {
	}

	// Constructor para respuestas exitosas con datos
	public ApiResponseDTO(T data) {
		this.success = true;
		this.message = "Operación exitosa";
		this.data = data;
	}

	// Constructor para respuestas exitosas con mensaje
	public ApiResponseDTO(String message) {
		this.success = true;
		this.message = message;
	}

	// Constructor para respuestas de error
	public ApiResponseDTO(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	// Constructor completo
	public ApiResponseDTO(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	// Métodos estáticos de utilidad para crear respuestas comunes
	public static <T> ApiResponseDTO<T> success(T data) {
		return new ApiResponseDTO<>(data);
	}

	public static <T> ApiResponseDTO<T> success(String message) {
		return new ApiResponseDTO<>(true, message);
	}

	public static <T> ApiResponseDTO<T> error(String message) {
		return new ApiResponseDTO<>(false, message);
	}

	// Getters y setters
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
