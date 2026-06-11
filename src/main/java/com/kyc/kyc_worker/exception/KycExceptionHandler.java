package com.kyc.kyc_worker.exception;

import java.util.Map;

public class KycExceptionHandler {

    public static Map<String, Object> handleException(
            Map<String, Object> variables,
            KycBusinessException exception,
            String stepName) {

        variables.put("kycStatus", "FAILED");
        variables.put("errorCode", exception.getErrorCode());
        variables.put("errorMessage", exception.getMessage());
        variables.put("errorStep", stepName);
        variables.put("hasError", true);

        System.out.println("=== KYC EXCEPTION OCCURRED ===");
        System.out.println("Step: " + stepName);
        System.out.println("Error Code: " + exception.getErrorCode());
        System.out.println("Error Message: " + exception.getMessage());

        return variables;
    }
}