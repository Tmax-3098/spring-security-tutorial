package com.sketch.springSecurity.advices;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public class ApiError {

    private String error;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
