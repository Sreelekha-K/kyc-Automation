package com.kyc.kyc_worker.exception;

public class FraudDetectionException
        extends KycBusinessException {

    public FraudDetectionException(
            String errorCode,
            String message) {

        super(errorCode, message);
    }
}
/*
Purpose:

Fraud service unavailable
Fraud score calculation failed
 */