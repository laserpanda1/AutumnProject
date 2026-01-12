package com.example.homeworkproject.dto;

import com.example.homeworkproject.entity.Account;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BillSetRequest(
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotBlank String number,
        @NotNull Long accountId
) {
}
