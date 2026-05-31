package com.kyc.kyc_worker.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FraudDetectionService {

    public Map<String, Object> detectFraud(
            Map<String, Object> variables) {

        String email =
                (String) variables.get("emailAddress");

        String phone =
                (String) variables.get("phoneNumber");

        String pan =
                (String) variables.get("panNumber");

        String aadhaar =
                (String) variables.get("aadhaarNumber");

        int fraudScore = 0;

        if (email != null &&
                (email.contains("fake")
                        || email.contains("test")
                        || email.contains("temp"))) {

            fraudScore += 20;
        }

        if (phone == null ||
                !phone.matches("\\+91[0-9]{10}")) {

            fraudScore += 25;
        }

        if (pan == null ||
                !pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {

            fraudScore += 15;
        }

        if (aadhaar == null ||
                !aadhaar.matches("\\d{12}")) {

            fraudScore += 15;
        }

        boolean isFraud = fraudScore >= 50;

        variables.put("fraudScore", fraudScore);
        variables.put("isFraud", isFraud);

        return variables;
    }
}