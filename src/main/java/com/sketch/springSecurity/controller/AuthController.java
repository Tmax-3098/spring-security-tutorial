package com.sketch.springSecurity.controller;

import com.sketch.springSecurity.dto.LoginDto;
import com.sketch.springSecurity.dto.SignupDto;
import com.sketch.springSecurity.dto.UserDto;
import com.sketch.springSecurity.services.LoginService;
import com.sketch.springSecurity.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        UserDto userDto = userService.signUp(signupDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = loginService.login(loginDto);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
