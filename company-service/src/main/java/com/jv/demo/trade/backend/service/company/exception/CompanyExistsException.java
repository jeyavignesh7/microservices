package com.jv.demo.trade.backend.service.company.exception;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyExistsException extends EntityExistsException {
    private CompanyErrorCodes errorCode = CompanyErrorCodes.DUPLICATE_RECORD;
    private String message = errorCode.getMessage();

    public CompanyExistsException(String message) {
        super(message);
    }
}
