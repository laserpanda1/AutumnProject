package com.example.homeworkproject.exception;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(Long billId) {
        super("Bill not found with id : " + billId);
    }

    public BillNotFoundException(String billNumber) {
      super("Bill not found with number : " + billNumber);
    }

    public BillNotFoundException(Long billId, Throwable cause) {
      super("Bill not found with id : " + billId , cause);
    }
}
