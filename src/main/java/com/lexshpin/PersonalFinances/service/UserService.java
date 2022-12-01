package com.lexshpin.PersonalFinances.service;

import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.repo.UserRepo;
import com.lexshpin.PersonalFinances.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findOne(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void update(String email, User userDTO) {
        userDTO.setUsername(email);

        userRepo.save(userDTO);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find a user with that username");
        }

        user.get().setBalance(0.0);

        return new UsersDetails(user.get());
    }

}
