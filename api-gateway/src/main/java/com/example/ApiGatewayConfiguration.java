package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiGatewayConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("eureka")
    private EurekaConfig eurekaConfig = new EurekaConfig();

    public EurekaConfig getEurekaConfig() {
        return eurekaConfig;
    }
}
