package com.sketch.springSecurity.services;

import com.sketch.springSecurity.entities.UserEntity;
import com.sketch.springSecurity.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepo.findByEmail(email);


    }
}
