package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SearchServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("eureka")
    private EurekaConfig eurekaConfig = new EurekaConfig();

    @Valid
    @NotNull
    @JsonProperty("elasticsearch")
    private ElasticsearchConfig elasticsearchConfig = new ElasticsearchConfig();

    public EurekaConfig getEurekaConfig() {
        return eurekaConfig;
    }

    public ElasticsearchConfig getElasticsearchConfig() {
        return elasticsearchConfig;
    }
}
