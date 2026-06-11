package com.kyc.kyc_worker.workflow;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SendFailureNotificationWorker {

    @JobWorker(type = "send-failure-notification")
    public Map<String, Object> sendFailureNotification(
            ActivatedJob job) {

        System.out.println("=== FAILURE NOTIFICATION STARTED ===");

        Map<String, Object> variables =
                job.getVariablesAsMap();

        String fullName =
                (String) variables.get("fullName");

        String email =
                (String) variables.get("emailAddress");

        String errorMessage =
                (String) variables.get("errorMessage");

        System.out.println(
                "Customer : " + fullName);

        System.out.println(
                "Email : " + email);

        System.out.println(
                "Error : " + errorMessage);

        System.out.println(
                "Failure Notification Sent");

        Map<String, Object> result =
                new HashMap<>();

        result.put(
                "failureNotificationSent",
                true);

        return result;
    }
}