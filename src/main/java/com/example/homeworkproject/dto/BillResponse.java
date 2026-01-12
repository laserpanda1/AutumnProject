package com.example.homeworkproject.dto;

import com.example.homeworkproject.entity.Bill;

import java.math.BigDecimal;

public record BillResponse(
        Long id,
        String number,
        BigDecimal amount

) {

    public static BillResponse fromEntity(Bill bill) {
        return new BillResponse(
                bill.getBillId(),
                bill.getNumber(),
                bill.getBalance()
        );
    }
}
