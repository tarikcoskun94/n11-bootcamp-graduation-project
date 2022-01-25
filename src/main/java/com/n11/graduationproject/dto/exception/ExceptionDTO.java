package com.n11.graduationproject.dto.exception;

import com.n11.graduationproject.dto.Responsable;
import lombok.*;

@Getter
@Setter
public class ExceptionDTO implements Responsable {

    private String exceptionPath;
}