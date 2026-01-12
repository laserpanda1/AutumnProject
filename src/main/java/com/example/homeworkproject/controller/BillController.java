package com.example.homeworkproject.controller;

import com.example.homeworkproject.dto.*;
import com.example.homeworkproject.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillController {

    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/setBill")
    public ResponseEntity<BillResponse> setBillForAccount(@RequestBody @Valid BillSetRequest request) {
        BillResponse response = billService.setBill(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<BillResponse> depositBill(@RequestBody @Valid DepositRequest request) {
        BillResponse response = billService.depositBill(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/transfers")
    public void transfer(@RequestBody @Valid TransferRequest request) {

    }
}
