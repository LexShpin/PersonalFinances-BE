package com.lexshpin.PersonalFinances.repo;

import com.lexshpin.PersonalFinances.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUsername(String email);
}
