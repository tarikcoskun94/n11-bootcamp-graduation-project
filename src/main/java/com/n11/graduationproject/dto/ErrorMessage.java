package com.n11.graduationproject.dto;

import com.n11.graduationproject.enum_.ReponseMessageType;

public class ErrorMessage extends BaseMessage {

    public ErrorMessage() {

        setType(ReponseMessageType.ERROR);
    }
}