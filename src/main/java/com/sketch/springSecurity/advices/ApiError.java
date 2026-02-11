package com.sketch.springSecurity.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private String error;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
