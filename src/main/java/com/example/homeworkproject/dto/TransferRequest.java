package com.example.homeworkproject.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TransferRequest(
      @NotBlank String fromBillNumber,
      @NotBlank  String toBillNumber,
      @DecimalMin("0.01") BigDecimal amount,
      String description
) {

    public TransferRequest {
        if(fromBillNumber.equals(toBillNumber)) {
            throw new IllegalArgumentException("Cannot transfer to same bill");
        }
    }
}
