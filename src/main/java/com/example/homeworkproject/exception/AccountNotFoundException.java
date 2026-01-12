package com.example.homeworkproject.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Account not found with id : " + accountId);
    }

    public AccountNotFoundException(String name) {
      super("Account not found with name : " + name);
    }
}
