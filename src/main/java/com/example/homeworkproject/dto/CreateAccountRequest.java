package com.example.homeworkproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(

        @NotBlank
        String name,

        @Email
        String email,

        @Size(min=4, message = "Password min = 4 chars")
        String password

) {
}
