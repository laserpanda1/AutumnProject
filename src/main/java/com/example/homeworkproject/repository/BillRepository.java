package com.example.homeworkproject.repository;

import com.example.homeworkproject.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    boolean existsByNumber(String number);

    Optional<Bill> findByNumber(String number);

    @Query("SELECT b.balance FROM Bill b WHERE b.number = :number")
    Optional<BigDecimal> findBalanceByNumber(@Param("number")String number);
}
