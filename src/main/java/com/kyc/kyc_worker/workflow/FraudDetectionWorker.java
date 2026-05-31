package com.kyc.kyc_worker.workflow;
import com.kyc.kyc_worker.service.FraudDetectionService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class FraudDetectionWorker {

    private final FraudDetectionService fraudDetectionService;

    @JobWorker(type="fraud-detection")
    public Map<String,Object> detectFraud(
            ActivatedJob job){

        return fraudDetectionService.detectFraud(
                job.getVariablesAsMap());
    }
}
//@Component
//public class FraudDetectionWorker {
//
//    @JobWorker(type = "fraud-detection")
//    public Map<String, Object> detectFraud(final ActivatedJob job) {
//
//        System.out.println("=== FRAUD DETECTION STARTED ===");
//
//        Map<String, Object> variables = job.getVariablesAsMap();
//
//        String pan = (String) variables.get("panNumber");
//        String aadhaar = (String) variables.get("aadhaarNumber");
//        String email = (String) variables.get("emailAddress");
//        String phone = (String) variables.get("phoneNumber");
//        String fullName = (String) variables.get("fullName");
//
//        int fraudScore = 0;
//
//        // -----------------------------
//        // RULE 1: Invalid identity mismatch signals
//        // -----------------------------
//        Boolean identityVerified = (Boolean) variables.get("identityVerified");
//        if (identityVerified == null || !identityVerified) {
//            fraudScore += 40;
//        }
//
//        // -----------------------------
//        // RULE 2: Suspicious email patterns
//        // -----------------------------
//        if (email != null) {
//            if (email.contains("test") || email.contains("fake") || email.contains("temp")) {
//                fraudScore += 20;
//            }
//        }
//
//        // -----------------------------
//        // RULE 3: Phone anomaly check
//        // -----------------------------
//        if (phone == null || !phone.matches("\\+91[0-9]{10}")) {
//            fraudScore += 25;
//        }
//
//        // -----------------------------
//        // RULE 4: PAN / Aadhaar weak format signals
//        // -----------------------------
//        if (pan == null || !pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
//            fraudScore += 15;
//        }
//
//        if (aadhaar == null || aadhaar.length() != 12) {
//            fraudScore += 15;
//        }
//
//        // -----------------------------
//        // RULE 5: Name inconsistency
//        // -----------------------------
//        if (fullName == null || fullName.trim().split(" ").length < 2) {
//            fraudScore += 10;
//        }
//
//        // -----------------------------
//        // FINAL FRAUD DECISION
//        // -----------------------------
//        boolean isFraud = fraudScore >= 50;
//
//        System.out.println("Fraud Score: " + fraudScore);
//        System.out.println("Is Fraud: " + isFraud);
//
//        // -----------------------------
//        // RETURN VARIABLES
//        // -----------------------------
//        variables.put("fraudScore", fraudScore);
//        variables.put("isFraud", isFraud);
//
//        return variables;
//    }
//}