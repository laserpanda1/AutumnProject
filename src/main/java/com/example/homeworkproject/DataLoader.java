package com.example.homeworkproject;

import com.example.homeworkproject.repository.AccountRepository;
import com.example.homeworkproject.repository.BillRepository;
import org.springframework.boot.CommandLineRunner;

import javax.xml.crypto.Data;

public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final BillRepository billRepository;

    public DataLoader(AccountRepository accountRepository, BillRepository billRepository) {
        this.accountRepository = accountRepository;
        this.billRepository = billRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
