package com.kyc.kyc_worker.exception;

public class NotificationException
        extends KycBusinessException {

    public NotificationException(
            String errorCode,
            String message) {

        super(errorCode, message);
    }
}
/*
Purpose:
Email notification failed
Kafka failed
SMS failed
 */