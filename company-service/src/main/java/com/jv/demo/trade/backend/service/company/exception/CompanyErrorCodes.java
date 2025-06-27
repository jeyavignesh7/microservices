package com.jv.demo.trade.backend.service.company.exception;

public enum CompanyErrorCodes {
    NOT_FOUND("Company Record Not Found"),
    DUPLICATE_RECORD("Company Record Already Exists");

    private final String message;
    CompanyErrorCodes(String messsage) {
        this.message = messsage;
    }
    public String getMessage() {
        return this.message;
    }
}
