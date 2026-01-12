package com.example.homeworkproject.repository;

import com.example.homeworkproject.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    boolean existsByNumber(String number);

    Optional<Bill> findByNumber(String number);
}
