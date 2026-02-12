package com.sketch.springSecurity.dto;

import lombok.Data;

@Data
public class SignupDto {

    private String email;
    private String password;
    private String name;
}
