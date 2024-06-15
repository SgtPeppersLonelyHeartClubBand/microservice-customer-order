package com.customer.service;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
