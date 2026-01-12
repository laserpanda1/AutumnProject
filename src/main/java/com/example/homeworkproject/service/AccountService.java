package com.example.homeworkproject.service;

import com.example.homeworkproject.dto.AccountResponse;
import com.example.homeworkproject.dto.BillResponse;
import com.example.homeworkproject.dto.BillSetRequest;
import com.example.homeworkproject.dto.CreateAccountRequest;
import com.example.homeworkproject.entity.Account;
import com.example.homeworkproject.entity.Bill;
import com.example.homeworkproject.repository.AccountRepository;
import com.example.homeworkproject.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final BillRepository billRepository;

    public AccountService(AccountRepository accountRepository, BillRepository billRepository) {
        this.accountRepository = accountRepository;
        this.billRepository = billRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setName(request.name());
        account.setEmail(request.email());
        accountRepository.save(account);

        return AccountResponse.fromEntity(account);
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(AccountResponse::fromEntity)
                .collect(Collectors.toList());
    }



}
