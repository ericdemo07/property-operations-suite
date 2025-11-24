package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NotificationServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("eureka")
    private EurekaConfig eurekaConfig = new EurekaConfig();

    @Valid
    @NotNull
    @JsonProperty("rabbitmq")
    private RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

    public EurekaConfig getEurekaConfig() {
        return eurekaConfig;
    }

    public RabbitMQConfig getRabbitMQConfig() {
        return rabbitMQConfig;
    }
}
