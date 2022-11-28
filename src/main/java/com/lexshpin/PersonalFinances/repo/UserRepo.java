package com.lexshpin.PersonalFinances.repo;

import com.lexshpin.PersonalFinances.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
