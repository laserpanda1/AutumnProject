package com.example.homeworkproject.dto;

import com.example.homeworkproject.entity.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record TransferResponse(
        String fromBillNumber,
        String toBillNumber,
        BigDecimal amount,
        BigDecimal senderNewBalance,
        BigDecimal receiverNewBalance,
        String status,
        String message,
        OffsetDateTime creationDate

) {

    public static TransferResponse success(Bill fromBill, Bill toBill, BigDecimal amount) {

        return new TransferResponse(
                fromBill.getNumber(),
                toBill.getNumber(),
                amount,
                fromBill.getBalance(),
                toBill.getBalance(),
                "SUCCESS",
                String.format("Transfer of %s completed successfully", amount),
                OffsetDateTime.now()
        );

    }

}
