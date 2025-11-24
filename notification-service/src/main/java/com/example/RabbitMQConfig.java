package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class RabbitMQConfig {

    @NotEmpty
    @JsonProperty
    private String hostname;

    @JsonProperty
    private int port;

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}
