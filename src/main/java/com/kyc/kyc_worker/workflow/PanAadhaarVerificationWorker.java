package com.kyc.kyc_worker.workflow;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PanAadhaarVerificationWorker {

    @JobWorker(type = "verify-pan-aadhaar")
    public Map<String, Object> verify(final ActivatedJob job) {

        System.out.println("=== PAN + AADHAAR VERIFICATION STARTED ===");

        Map<String, Object> variables = job.getVariablesAsMap();

        String pan = (String) variables.get("panNumber");
        String aadhaar = (String) variables.get("aadhaarNumber");

        // -----------------------------
        // PAN VALIDATION
        // Format: 5 letters + 4 digits + 1 letter
        // Example: ABCDE1234F
        // -----------------------------
        boolean panValid = pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");

        // -----------------------------
        // AADHAAR VALIDATION
        // Basic check: 12 digits
        // -----------------------------
        boolean aadhaarValid = aadhaar != null && aadhaar.matches("\\d{12}");

        // -----------------------------
        // FINAL DECISION
        // -----------------------------
        boolean identityVerified = panValid && aadhaarValid;

        System.out.println("PAN Valid: " + panValid);
        System.out.println("Aadhaar Valid: " + aadhaarValid);
        System.out.println("Identity Verified: " + identityVerified);

        // -----------------------------
        // RETURN VARIABLES TO BPMN
        // -----------------------------
        variables.put("panValid", panValid);
        variables.put("aadhaarValid", aadhaarValid);
        variables.put("identityVerified", identityVerified);

        return variables;
    }
}