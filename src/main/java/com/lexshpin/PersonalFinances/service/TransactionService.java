package com.lexshpin.PersonalFinances.service;

import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.repo.TransactionRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    public List<Transaction> findAllByUserEmail(String email) {
        return transactionRepo.findAllByUserEmail(email);
    }

    public Transaction findOne(int id) {
        return transactionRepo.findById(id).orElse(null);
    }

    public void save(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    public void update(int id, Transaction transaction) {
        transaction.setId(id);

        transactionRepo.save(transaction);
    }

    public void delete(int id) {
        transactionRepo.deleteById(id);
    }
}
