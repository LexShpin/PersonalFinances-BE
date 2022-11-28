package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.dto.UserDTO;
import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.service.TransactionService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    @Autowired
    public DashboardController(UserService userService, TransactionService transactionService, ModelMapper modelMapper) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<User> loadDashboard(@RequestBody UserDTO userDTO, String userEmail) {
        User user = userService.findByEmail(userEmail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getUserTransactions(String userEmail) {
        List<Transaction> userTransactions = transactionService.findAllByUserEmail(userEmail);
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @PostMapping("/updateBalance")
    public ResponseEntity<User> updateUserBalance(@RequestBody UserDTO userDTO, double newBalance) {
        userDTO.setBalance(newBalance);
        User user = convertToUser(userDTO);

        userService.update(user.getEmail(), user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
