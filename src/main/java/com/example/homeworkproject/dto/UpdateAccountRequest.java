package com.example.homeworkproject.dto;

public record UpdateAccountRequest(
        String name,
        String email,
        String password
) {

    public UpdateAccountRequest {
        if(name != null && name.isBlank()) {
            throw new IllegalArgumentException("Name cant be blank");
        }
    }
}
