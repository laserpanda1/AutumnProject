package com.example.homeworkproject.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest (
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be grater than 0")
    BigDecimal amount,

    String billNumber
) {

    public boolean hasBillNumber() {
        return billNumber != null && !billNumber.trim().isEmpty();
    }
}
