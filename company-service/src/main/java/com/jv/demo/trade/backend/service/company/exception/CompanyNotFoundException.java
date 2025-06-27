package com.jv.demo.trade.backend.service.company.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyNotFoundException extends EntityNotFoundException {
    private CompanyErrorCodes errorCode = CompanyErrorCodes.NOT_FOUND;
    private String message = errorCode.getMessage();

    private CompanyNotFoundException(String message) {
        super(message);
    }
}
