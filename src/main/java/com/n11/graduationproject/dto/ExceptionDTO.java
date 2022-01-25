package com.n11.graduationproject.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExceptionDTO {

    private String errorTitle;
    private List<String> errorMessages;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
}