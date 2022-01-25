package com.n11.graduationproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.graduationproject.enum_.ReponseMessageType;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class BaseMessage {

    private ReponseMessageType type;
    private HttpStatus httpStatus;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime timeStamp;
    private List<String> messages;
    private List<Responsable> content;

    public ReponseMessageType getType() {
        return type;
    }

    protected void setType(ReponseMessageType type) {
        this.type = type;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setMessages(String... messages) {
        this.messages = Arrays.asList(messages);
    }

    public List<Responsable> getContent() {
        return content;
    }

    public void setContent(List<Responsable> content) {
        this.content = content;
    }

    public void setContent(Responsable... content) {
        this.content = Arrays.asList(content);
    }
}
