package com.sketch.springSecurity.services;

import com.sketch.springSecurity.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<UserEntity> findByEmail(String email);
}
