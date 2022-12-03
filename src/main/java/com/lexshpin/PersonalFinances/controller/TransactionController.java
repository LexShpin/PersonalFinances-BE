package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.dto.TransactionDTO;
import com.lexshpin.PersonalFinances.dto.UserDTO;
import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Transaction>> getAllUserTransactions(@PathVariable("username") String username) {
        List<Transaction> transactions = transactionService.findAllByUsername(username);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = new ArrayList<>();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
//        Transaction transaction = convertToTransaction(transactionService);
        transactionService.save(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable("id") int id) {

        transactionService.update(id, transaction);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") int id) {

        transactionService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Transaction convertToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
