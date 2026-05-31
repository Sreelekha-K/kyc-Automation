package com.kyc.kyc_worker.workflow;

import com.kyc.kyc_worker.service.RiskAssessmentService;
import io.camunda.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RiskAssessmentWorker {

    private final RiskAssessmentService riskAssessmentService;

    @JobWorker(type = "risk-assessment")
    public Map<String, Object> assessRisk(
            ActivatedJob job) {

        System.out.println("=== RISK ASSESSMENT WORKER STARTED ===");

        return riskAssessmentService.calculateRisk(
                job.getVariablesAsMap()
        );
    }
}