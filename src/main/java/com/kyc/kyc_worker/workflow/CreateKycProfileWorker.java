package com.kyc.kyc_worker.workflow;

import com.kyc.kyc_worker.service.KycService;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateKycProfileWorker {

    private final KycService kycService;

    @JobWorker(type="create-kyc-profile")
    public Map<String,Object> createProfile(
            ActivatedJob job){

        Map<String,Object> vars =
                job.getVariablesAsMap();

        vars.put(
                "kycId",
                kycService.createKycProfile());

        return vars;
    }
}