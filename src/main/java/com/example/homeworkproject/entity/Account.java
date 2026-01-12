package com.example.homeworkproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String name;

    private String email;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL
    )
    List<Bill> bills = new ArrayList<>();

    public Account(String name, String email, List<Bill> bills) {
        this.name = name;
        this.email = email;
        this.bills = bills;
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setAccount(this);
    }

    public void removeBill(Bill bill) {
        bills.remove(bill);
        bill.setAccount(null);
    }
}
