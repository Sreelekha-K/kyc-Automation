package com.kyc.kyc_worker.workflow;

import io.camunda.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationWorker {

    @JobWorker(type = "send-notification")
    public Map<String, Object> notifyCustomer(
            ActivatedJob job) {

        System.out.println("=== NOTIFICATION STARTED ===");

        Map<String, Object> variables =
                job.getVariablesAsMap();

        String email =
                (String) variables.get("emailAddress");

        String fullName =
                (String) variables.get("fullName");

        Boolean kycApproved =
                Boolean.valueOf(
                        String.valueOf(
                                variables.get("kycApproved")));

        String status =
                kycApproved
                        ? "APPROVED"
                        : "REJECTED";

        System.out.println("Customer: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Status: " + status);

        if ("APPROVED".equals(status)) {

            System.out.println(
                    "Email Sent: Dear "
                            + fullName
                            + ", your KYC has been APPROVED.");

        } else {

            System.out.println(
                    "Email Sent: Dear "
                            + fullName
                            + ", your KYC has been REJECTED.");
        }

        variables.put("kycStatus", status);

        return variables;
    }
}