package com.carte.clouds5spring.dto;

public class ApiResponse<T> 
{
    private String status;
    private T data;
    private String error;

    public ApiResponse() {}

    public ApiResponse(String status, T data, String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    // Méthodes utilitaires
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, null);
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>("error", null, errorMessage);
    }

    // getters & setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    
}
