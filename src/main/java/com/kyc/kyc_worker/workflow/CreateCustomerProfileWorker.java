package com.kyc.kyc_worker.workflow;

import com.kyc.kyc_worker.service.KycService;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateCustomerProfileWorker {

    private final KycService kycService;

    @JobWorker(type = "create-customer-profile")
    public Map<String, Object> createCustomer(
            ActivatedJob job) {

        Map<String, Object> vars =
                job.getVariablesAsMap();

        String customerId =
                kycService.createCustomerProfile();

        vars.put("customerId", customerId);

        vars.put(
                "customerName",
                vars.get("fullName")
        );

        vars.put(
                "customerCreated",
                true
        );

        return vars;
    }
}