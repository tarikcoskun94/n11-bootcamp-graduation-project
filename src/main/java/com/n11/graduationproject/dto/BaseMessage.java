package com.n11.graduationproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.graduationproject.enum_.ReponseMessageType;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
public abstract class BaseMessage {

    private ReponseMessageType type;
    private HttpStatus httpStatus;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime timeStamp;
    private List<String> messages;
    private List<Responsable> content;

    public BaseMessage() {
        this.messages = new ArrayList<>();
        this.content = new ArrayList<>();
    }

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
        this.messages.clear();
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public List<Responsable> getContent() {
        return content;
    }

    public void setContent(List<Responsable> content) {
        this.content.clear();
        this.content = content;
    }

    public void addContent(Responsable content) {
        this.content.add(content);
    }
}
