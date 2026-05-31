package com.kyc.kyc_worker.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RiskAssessmentService {

    public Map<String, Object> calculateRisk(
            Map<String, Object> variables) {

        String email = (String) variables.get("emailAddress");
        String phone = (String) variables.get("phoneNumber");
        String aadhaar = (String) variables.get("aadhaarNumber");

        int riskScore = 0;

        Boolean identityVerified =
                (Boolean) variables.get("identityVerified");

        if (identityVerified == null || !identityVerified) {
            riskScore += 40;
        }

        if (email != null && email.contains("test")) {
            riskScore += 15;
        }

        if (phone == null || !phone.matches("\\+91[0-9]{10}")) {
            riskScore += 20;
        }

        if (aadhaar == null || aadhaar.length() != 12) {
            riskScore += 25;
        }

        String riskLevel;
        boolean highRisk;

        if (riskScore <= 20) {
            riskLevel = "LOW";
            highRisk = false;
        } else if (riskScore <= 50) {
            riskLevel = "MEDIUM";
            highRisk = false;
        } else {
            riskLevel = "HIGH";
            highRisk = true;
        }

        System.out.println("=== RISK ASSESSMENT ===");
        System.out.println("Risk Score: " + riskScore);
        System.out.println("Risk Level: " + riskLevel);
        System.out.println("High Risk : " + highRisk);

        variables.put("riskScore", riskScore);
        variables.put("riskLevel", riskLevel);
        variables.put("highRisk", highRisk);

        return variables;
    }
}