package com.parse.broker.entities;

import java.io.Serializable;

public class Response implements Serializable {

    private Status status;
    private String message;

    public enum Status{
        SUCCESS,
        FAILURE
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
