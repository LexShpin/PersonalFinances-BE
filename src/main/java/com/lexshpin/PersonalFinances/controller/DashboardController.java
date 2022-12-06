package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.dto.UserDTO;
import com.lexshpin.PersonalFinances.model.Transaction;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.security.UsersDetails;
import com.lexshpin.PersonalFinances.service.TransactionService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> loadDashboard(@PathVariable("username") String username) {

        UsersDetails currentUser = userService.loadUserByUsername(username);

        UserDTO userDTO = convertToUserDTO(currentUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getUserTransactions(@RequestBody UserDTO userDTO) {
        List<Transaction> userTransactions = transactionService.findAllByUsername(userDTO.getUsername());
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @PostMapping("/{username}/updateBalance")
    public ResponseEntity<UserDTO> updateUserBalance(@PathVariable("username") String username, @RequestBody UserDTO passedUser) {

        UsersDetails foundUser = userService.loadUserByUsername(passedUser.getUsername());
        foundUser.setBalance(passedUser.getBalance());

        userService.update(foundUser.getUsername(), foundUser.getUser());

        UserDTO userDTO = convertToUserDTO(foundUser);


        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private UserDTO convertToUserDTO(UsersDetails usersDetails) {
        return modelMapper.map(usersDetails, UserDTO.class);
    }

}
