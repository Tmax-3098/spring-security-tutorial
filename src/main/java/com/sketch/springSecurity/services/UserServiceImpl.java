package com.sketch.springSecurity.services;

import com.sketch.springSecurity.entities.UserEntity;
import com.sketch.springSecurity.exception.ResourceNotFoundException;
import com.sketch.springSecurity.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user does not exists with email "+username));
    }
}

