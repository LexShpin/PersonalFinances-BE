package com.lexshpin.PersonalFinances.controller;

import com.lexshpin.PersonalFinances.dto.UserDTO;
import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.security.UsersDetails;
import com.lexshpin.PersonalFinances.service.RegistrationService;
import com.lexshpin.PersonalFinances.service.UserService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, AuthenticationManager authenticationManager, UserService userService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {

        registrationService.register(user);

        UserDTO userDTO = convertToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        UsersDetails currentUser = userService.loadUserByUsername(user.getUsername());

        try {
            authenticationManager.authenticate(authenticationToken);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDTO userDTO = convertToUserDTO(currentUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserDTO convertToUserDTO(UsersDetails user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
