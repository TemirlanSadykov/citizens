package com.java.citizens.service;

import com.java.citizens.entity.QUser;
import com.java.citizens.entity.User;
import com.java.citizens.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.NoSuchElementException;


public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, InternalAuthenticationServiceException, NoSuchElementException {

        if (userRepo.findOne(QUser.user.login.eq(username)).isPresent()) {
            User user = userRepo.findOne(QUser.user.login.eq(username)).get();
            UserBuilder builder;
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRole().getName());
            return builder.build();
        }

        throw new UsernameNotFoundException("User not found.");

    }

}