package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.service.TransactionService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;
    private final TransactionService transactionService;
    @Autowired
    public DashboardController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetails> loadDashboard(@PathVariable("username") String username) {

        UserDetails currentUser = userService.loadUserByUsername(username);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getUserTransactions(@RequestBody User user) {
        List<Transaction> userTransactions = transactionService.findAllByUsername(user.getUsername());
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @PostMapping("/updateBalance")
    public ResponseEntity<User> updateUserBalance(@RequestBody User user, double newBalance) {
        user.setBalance(newBalance);

        userService.update(user.getUsername(), user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
