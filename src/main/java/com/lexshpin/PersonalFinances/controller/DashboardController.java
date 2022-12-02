package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.security.UsersDetails;
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

        UsersDetails currentUser = userService.loadUserByUsername(username);
        System.out.println(currentUser.getBalance());

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getUserTransactions(@RequestBody User user) {
        List<Transaction> userTransactions = transactionService.findAllByUsername(user.getUsername());
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @PostMapping("/{username}/updateBalance")
    public ResponseEntity<UsersDetails> updateUserBalance(@PathVariable("username") String username, @RequestBody User passedUser) {

        UsersDetails foundUser = userService.loadUserByUsername(passedUser.getUsername());
        foundUser.setBalance(passedUser.getBalance());
        userService.update(foundUser.getUsername(), foundUser.getUser());


        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

}
