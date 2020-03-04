package com.altimetrik.microservices.limitsservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.microservices.limitsservice.configuration.BaseConfig;
import com.altimetrik.microservices.limitsservice.configuration.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private BaseConfig baseConfig;

    @GetMapping("/limits/v1")
    public LimitConfiguration getLimitConfigurationV1() {
        return new LimitConfiguration(1, 1000);
    }

    @GetMapping("/limits/v2")
    public LimitConfiguration getLimitConfigurationV2() {
        return new LimitConfiguration(baseConfig.getMin(), baseConfig.getMax());
    }

    @GetMapping("/fault-url")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public LimitConfiguration getLimitConfigurationV3() {
        throw new RuntimeException("Not Available");
    }

    public LimitConfiguration fallbackRetrieveConfiguration() {
        return new LimitConfiguration(10, 10000);
    }
}
