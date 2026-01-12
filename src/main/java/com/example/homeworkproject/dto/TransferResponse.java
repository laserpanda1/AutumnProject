package com.example.homeworkproject.dto;

import java.math.BigDecimal;

public record TransferResponse(
        String fromBillNumber,
        String toBillNumber,
        BigDecimal amount,

        BigDecimal newFromBillBalance,
        BigDecimal newToBillBalance
) {
}
