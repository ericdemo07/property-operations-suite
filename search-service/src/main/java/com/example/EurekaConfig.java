package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class EurekaConfig {

    @NotEmpty
    @JsonProperty("serviceUrl")
    private String serviceUrl;

    @NotEmpty
    @JsonProperty("serviceName")
    private String serviceName;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }
}
