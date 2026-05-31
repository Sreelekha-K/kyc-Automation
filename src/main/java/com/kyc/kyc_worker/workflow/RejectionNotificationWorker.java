package com.kyc.kyc_worker.workflow;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RejectionNotificationWorker {

    @JobWorker(type = "send-rejection-notification")
    public void reject(
            ActivatedJob job) {

        Map<String, Object> variables =
                job.getVariablesAsMap();

        String email =
                (String) variables.get("emailAddress");

        System.out.println(
                "Rejection mail sent to : "
                        + email);
    }
}