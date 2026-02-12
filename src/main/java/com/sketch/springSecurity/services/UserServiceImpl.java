package com.sketch.springSecurity.services;

import com.sketch.springSecurity.dto.SignupDto;
import com.sketch.springSecurity.dto.UserDto;
import com.sketch.springSecurity.entities.UserEntity;
import com.sketch.springSecurity.exception.ResourceNotFoundException;
import com.sketch.springSecurity.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user does not exists with email "+username));
    }

    public UserDto signUp(SignupDto signupDto) {
        Optional<UserEntity> user = userRepo.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User already exists with the email "+signupDto.getEmail());
        }

        UserEntity newUser = mapper.map(signupDto, UserEntity.class);
        return mapper.map(userRepo.save(newUser), UserDto.class);
    }
}

