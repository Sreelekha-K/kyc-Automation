package com.kyc.kyc_worker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String customerId;

    private String fullName;

    private String panNumber;

    private String aadhaarNumber;

    private String emailAddress;

    private String phoneNumber;

    private Integer riskScore;

    private String riskLevel;

    private String kycStatus;
}