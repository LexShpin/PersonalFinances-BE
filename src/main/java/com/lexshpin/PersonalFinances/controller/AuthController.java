package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.service.RegistrationService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService, AuthenticationManager authenticationManager, UserService userService) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        registrationService.register(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> loginUser(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        UserDetails currentUser = userService.loadUserByUsername(user.getUsername());

        try {
            authenticationManager.authenticate(authenticationToken);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
