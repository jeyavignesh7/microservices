package com.jv.demo.trade.backend.service.location.exception;

public enum CountryErrorCodes {
    NOT_FOUND("Country Record Not Found"),
    DUPLICATE_RECORD("Country Record Already Exists");

    private final String message;
    CountryErrorCodes(String messsage) {
        this.message = messsage;
    }
    public String getMessage() {
        return this.message;
    }
}
