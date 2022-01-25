package com.n11.graduationproject.dto;

import com.n11.graduationproject.enum_.ReponseMessageType;

public class SuccessMessage extends BaseMessage {

    public SuccessMessage() {

        setType(ReponseMessageType.SUCCESS);
    }
}
