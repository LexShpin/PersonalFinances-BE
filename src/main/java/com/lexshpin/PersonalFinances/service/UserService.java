package com.lexshpin.PersonalFinances.service;

import com.lexshpin.PersonalFinances.model.User;
import com.lexshpin.PersonalFinances.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

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

    public void update(int id, User user) {
        user.setId(id);

        userRepo.save(user);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }
}
