package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class EurekaConfig {

    @NotEmpty
    @JsonProperty
    private String serviceUrl;

    @NotEmpty
    @JsonProperty
    private String serviceName;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }
}
