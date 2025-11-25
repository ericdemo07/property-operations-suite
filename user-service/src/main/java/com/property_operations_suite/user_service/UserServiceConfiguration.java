package com.property_operations_suite.user_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty("eureka")
    private EurekaConfig eurekaConfig = new EurekaConfig();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public EurekaConfig getEurekaConfig() {
        return eurekaConfig;
    }
}
