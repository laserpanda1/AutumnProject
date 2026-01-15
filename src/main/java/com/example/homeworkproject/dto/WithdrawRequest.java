package com.example.homeworkproject.dto;

import java.math.BigDecimal;

public record WithdrawRequest(
        String billNumber,
        BigDecimal amount
) {
}
