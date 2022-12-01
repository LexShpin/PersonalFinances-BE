package com.lexshpin.PersonalFinances.security;

import com.lexshpin.PersonalFinances.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsersDetails implements UserDetails {

    private final com.lexshpin.PersonalFinances.model.User user;

    public UsersDetails(com.lexshpin.PersonalFinances.model.User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    public Double getBalance() {
        return this.user.getBalance();
    }

    public void setBalance(Double balance) {
        this.user.setBalance(balance);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return this.user;
    }
}
