package com.example.homeworkproject.service;

import com.example.homeworkproject.dto.*;
import com.example.homeworkproject.entity.Account;
import com.example.homeworkproject.entity.Bill;
import com.example.homeworkproject.exception.BillNotFoundException;
import com.example.homeworkproject.repository.AccountRepository;
import com.example.homeworkproject.repository.BillRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
public class BillService {

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;

    public BillService(BillRepository billRepository, AccountRepository accountRepository) {
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;
    }

    public BillResponse withdrawBill(WithdrawRequest request) {
        Bill bill = billRepository.findByNumber(request.billNumber())
                .orElseThrow(() -> new BillNotFoundException(request.billNumber()));

        if(bill.getBalance().compareTo(request.amount()) < 0) {
            throw new IllegalArgumentException("No money - no funny ;( ");
        }

        BigDecimal oldBalance = bill.getBalance();

        bill.setBalance(bill.getBalance().subtract(request.amount()));
        Bill savedBill = billRepository.save(bill);
        log.info("Old balance : {}, New balance : {}, Amount: {}" , oldBalance, bill.getBalance(), request.amount());

        return BillResponse.fromEntity(savedBill);
    }


    public BigDecimal checkBalance(String number) {
        return billRepository.findBalanceByNumber(number)
                .orElseThrow(() -> new BillNotFoundException(number));
    }


    public TransferResponse transfer(TransferRequest request) {
        Bill fromBill = billRepository.findByNumber(request.fromBillNumber())
                .orElseThrow(() -> new BillNotFoundException(request.fromBillNumber()));

        Bill toBill = billRepository.findByNumber(request.toBillNumber())
                .orElseThrow(() -> new BillNotFoundException(request.toBillNumber()));

        if(request.amount().compareTo(fromBill.getBalance()) > 0) {
            throw new IllegalArgumentException("Not enough money on the balance");
        }

        fromBill.setBalance(fromBill.getBalance().subtract(request.amount()));
        toBill.setBalance(toBill.getBalance().add(request.amount()));

        billRepository.save(fromBill);
        billRepository.save(toBill);

        log.info("Трансфер выполнился успешно !!!");
        log.info("Перевод на сумму : {}", request.amount());

        return TransferResponse.success(fromBill, toBill, request.amount());
    }


    public BillResponse setBill(BillSetRequest request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new BillNotFoundException(request.accountId()));

        Bill bill = new Bill();
        bill.setNumber(request.number());
        bill.setBalance(request.amount());
        bill.setAccount(account);

        account.getBills().add(bill);

        Bill savedBill = billRepository.save(bill);

        return BillResponse.fromEntity(savedBill);
    }


    public BillResponse depositBill(DepositRequest request) {

        validateDeposit(request);

        Bill bill = billRepository.findByNumber(request.billNumber())
                .orElseThrow(() -> new BillNotFoundException(request.billNumber()));

        BigDecimal result = bill.getBalance().add(request.amount());
        bill.setBalance(result);

        Bill savedBill = billRepository.save(bill);

        return BillResponse.fromEntity(savedBill);
    }


    private void validateDeposit(DepositRequest request) {
        if(request.amount() == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if(request.amount().compareTo(BigDecimal.ZERO ) <= 0) {
            throw new IllegalArgumentException("Amount must be grater than 0");
        }

        if(request.billNumber() == null || request.billNumber().isBlank()) {
            throw new IllegalArgumentException("Bill number is required");
        }
    }
}
