package com.lexshpin.PersonalFinances.repo;

import com.lexshpin.PersonalFinances.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
