package com.example.homeworkproject.dto;

import com.example.homeworkproject.entity.Account;

import java.util.List;

public record AccountResponse(
        Long id,
        String name,
        String email,
        List<BillResponse> bills
) {

    public static AccountResponse fromEntity(Account account) {
        return new AccountResponse(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.getBills().stream()
                        .map(BillResponse::fromEntity)
                        .toList()
        );
    }
}
