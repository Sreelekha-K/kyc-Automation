package com.kyc.kyc_worker.workflow;

import com.kyc.kyc_worker.constant.KycErrorCode;
import com.kyc.kyc_worker.exception.KycExceptionHandler;
import com.kyc.kyc_worker.exception.ValidationException;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DocumentValidationWorker {

    @JobWorker(type = "validate-documents")
    public Map<String, Object> validateDocuments(
            final ActivatedJob job) {

        System.out.println("=== DOCUMENT VALIDATION STARTED ===");

        Map<String, Object> variables =
                job.getVariablesAsMap();

        try {
            String fullName =
                    (String) variables.get("fullName");

            String aadhaarNumber =
                    (String) variables.get("aadhaarNumber");

            String panNumber =
                    (String) variables.get("panNumber");

            String emailAddress =
                    (String) variables.get("emailAddress");

            String phoneNumber =
                    (String) variables.get("phoneNumber");

            if (fullName == null || fullName.trim().length() < 3) {
                throw new ValidationException(
                        KycErrorCode.INVALID_NAME,
                        "Customer name is missing or too short."
                );
            }

            if (aadhaarNumber == null ||
                    !aadhaarNumber.matches("\\d{12}")) {

                throw new ValidationException(
                        KycErrorCode.INVALID_AADHAAR,
                        "Aadhaar number must contain exactly 12 digits."
                );
            }

            if (panNumber == null ||
                    !panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {

                throw new ValidationException(
                        KycErrorCode.INVALID_PAN,
                        "PAN number must be in valid format. Example: ABCDE1234F"
                );
            }

            if (emailAddress == null ||
                    !emailAddress.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                throw new ValidationException(
                        KycErrorCode.INVALID_EMAIL,
                        "Email address is invalid."
                );
            }

            if (phoneNumber == null ||
                    !phoneNumber.matches("^\\+[0-9]{10,15}$")) {

                throw new ValidationException(
                        KycErrorCode.INVALID_PHONE,
                        "Phone number must start with country code. Example: +919876543210"
                );
            }

            variables.put("documentsComplete", true);
            variables.put("hasError", false);
            variables.put("kycStatus", "DOCUMENTS_VALIDATED");

            System.out.println("Documents validation successful.");

            return variables;

        } catch (ValidationException exception) {

            variables.put("documentsComplete", false);

            return KycExceptionHandler.handleException(
                    variables,
                    exception,
                    "Validate Documents"
            );
        }
    }
}