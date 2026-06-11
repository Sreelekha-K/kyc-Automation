package com.kyc.kyc_worker.exception;

public class KycBusinessException extends RuntimeException {

    private final String errorCode;

    public KycBusinessException(
            String errorCode,
            String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
/*
Purpose:
Custom business exceptions
INVALID_PAN
INVALID_AADHAAR
INVALID_EMAIL
etc.
*/
