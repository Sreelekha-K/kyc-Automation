package com.kyc.kyc_worker.exception;

public class ValidationException
        extends KycBusinessException {

    public ValidationException(
            String errorCode,
            String message) {

        super(errorCode, message);
    }
}

/*
Purpose:Customer entered bad data
Examples: Wrong PAN,Wrong Aadhaar,Wrong Email ,Wrong Phone
 */