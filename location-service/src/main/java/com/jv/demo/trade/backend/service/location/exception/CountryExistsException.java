package com.jv.demo.trade.backend.service.location.exception;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryExistsException extends EntityExistsException {
    private CountryErrorCodes errorCode = CountryErrorCodes.DUPLICATE_RECORD;
    private String message = errorCode.getMessage();

    public CountryExistsException(String message) {
        super(message);
    }
}
