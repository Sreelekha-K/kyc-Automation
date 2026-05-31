package com.kyc.kyc_worker.workflow;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DocumentValidationWorker {

    @JobWorker(type = "validate-documents")
    public Map<String, Object> validateDocuments(
            final ActivatedJob job) {

        boolean documentsComplete = true;

        return Map.of(
                "documentsComplete",
                documentsComplete
        );
    }
}