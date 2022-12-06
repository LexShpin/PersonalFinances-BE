package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.TransactionCategories;
import com.lexshpin.PersonalFinances.security.UsersDetails;
import com.lexshpin.PersonalFinances.service.TransactionService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Transaction>> getAllUserTransactions(@PathVariable("username") String username) {
        List<Transaction> transactions = transactionService.findAllByUsername(username);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<TransactionCategories[]>> getAllCategories() {
        List<TransactionCategories[]> categories = Collections.singletonList(TransactionCategories.values());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/{username}/create")
    public ResponseEntity<Transaction> createTransaction(@PathVariable("username") String username, @RequestBody Transaction transaction) {;

        UsersDetails existingUser = userService.loadUserByUsername(username);
        System.out.println(existingUser);

        if (existingUser.getBalance() - transaction.getAmount() < 0) {
            return new ResponseEntity<>(transaction, HttpStatus.FORBIDDEN);
        }

        existingUser.setBalance(existingUser.getBalance() - transaction.getAmount());
        transactionService.save(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PatchMapping("/{username}/{id}/update")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("username") String username, @RequestBody Transaction transaction, @PathVariable("id") int id) {

        UsersDetails existingUser = userService.loadUserByUsername(username);

        if (existingUser.getBalance() - transaction.getAmount() < 0) {
            return new ResponseEntity<>(transaction, HttpStatus.FORBIDDEN);
        }

        existingUser.setBalance(existingUser.getBalance() - transaction.getAmount());
        transactionService.update(id, transaction);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{username}/{id}/delete")
    public ResponseEntity<?> deleteTransaction(@PathVariable("username") String username, @PathVariable("id") int id) {

        UsersDetails existingUser = userService.loadUserByUsername(username);
        Transaction transaction = transactionService.findOne(id);

        existingUser.setBalance(existingUser.getBalance() + transaction.getAmount());
        transactionService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
