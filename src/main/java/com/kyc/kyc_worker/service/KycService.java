package com.kyc.kyc_worker.service;

import org.springframework.stereotype.Service;

@Service
public class KycService {

    public String createKycProfile() {

        return "KYC-" +
                System.currentTimeMillis();
    }

    public String createCustomerProfile() {

        return "CUST-" +
                System.currentTimeMillis();
    }
}