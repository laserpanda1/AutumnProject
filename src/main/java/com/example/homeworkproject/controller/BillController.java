package com.example.homeworkproject.controller;

import com.example.homeworkproject.dto.*;
import com.example.homeworkproject.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/bills/{billNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String billNumber) {
        BigDecimal balance = billService.checkBalance(billNumber);

        return ResponseEntity.ok(balance);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BillResponse> withdraw(@RequestBody WithdrawRequest request) {
        BillResponse response = billService.withdrawBill(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
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
    public ResponseEntity<TransferResponse> transfer(@RequestBody @Valid TransferRequest request) {
        TransferResponse response = billService.transfer(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
