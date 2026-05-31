package com.kyc.kyc_worker.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendApprovalMail(
            String email,
            String customerName) {

        System.out.println(
                "APPROVAL EMAIL SENT TO : "
                        + customerName
                        + " -> "
                        + email);
    }

    public void sendRejectionMail(
            String email,
            String customerName) {

        System.out.println(
                "REJECTION EMAIL SENT TO : "
                        + customerName
                        + " -> "
                        + email);
    }
}