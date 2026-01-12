package com.example.homeworkproject.controller;

import com.example.homeworkproject.dto.AccountResponse;
import com.example.homeworkproject.dto.BillResponse;
import com.example.homeworkproject.dto.BillSetRequest;
import com.example.homeworkproject.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
