package com.jv.demo.trade.backend.service.location.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryNotFoundException extends EntityNotFoundException {
    private CountryErrorCodes errorCode = CountryErrorCodes.NOT_FOUND;
    private String message = errorCode.getMessage();

    private CountryNotFoundException(String message) {
        super(message);
    }
}
