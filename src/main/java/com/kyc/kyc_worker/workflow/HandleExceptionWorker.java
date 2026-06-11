package com.kyc.kyc_worker.workflow;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HandleExceptionWorker {

    @JobWorker(type = "handle-exception")
    public Map<String, Object> handleException(
            ActivatedJob job) {

        Map<String, Object> variables =
                job.getVariablesAsMap();

        System.out.println("=== GLOBAL EXCEPTION HANDLER ===");

        System.out.println(
                "Error Code : " +
                        variables.get("errorCode"));

        System.out.println(
                "Error Message : " +
                        variables.get("errorMessage"));

        System.out.println(
                "Failed Step : " +
                        variables.get("errorStep"));

        variables.put(
                "kycStatus",
                "FAILED");

        return variables;
    }
}