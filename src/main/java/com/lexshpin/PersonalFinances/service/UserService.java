package com.lexshpin.PersonalFinances.service;

import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void update(String email, User user) {
        user.setEmail(email);

        userRepo.save(user);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
