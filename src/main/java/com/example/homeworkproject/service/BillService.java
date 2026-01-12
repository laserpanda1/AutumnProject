package com.example.homeworkproject.service;

import com.example.homeworkproject.dto.BillResponse;
import com.example.homeworkproject.dto.BillSetRequest;
import com.example.homeworkproject.entity.Account;
import com.example.homeworkproject.entity.Bill;
import com.example.homeworkproject.exception.BillNotFoundException;
import com.example.homeworkproject.repository.AccountRepository;
import com.example.homeworkproject.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BillService {

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;

    public BillService(BillRepository billRepository, AccountRepository accountRepository) {
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;
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
}
