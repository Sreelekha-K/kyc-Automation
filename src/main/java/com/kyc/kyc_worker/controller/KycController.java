package com.kyc.kyc_worker.controller;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kyc")
@RequiredArgsConstructor
public class KycController {

    private final ZeebeClient zeebeClient;

    @PostMapping("/start")
    public String startProcess() {

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("kyc-process")
                .latestVersion()
                .send()
                .join();

        return "KYC Started";
    }
}