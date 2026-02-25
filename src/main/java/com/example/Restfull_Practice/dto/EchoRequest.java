package com.example.Restfull_Practice.dto;
public class EchoRequest {
    private String name;
    private String message;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}