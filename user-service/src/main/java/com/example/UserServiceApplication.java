package com.example;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;

public class UserServiceApplication extends Application<UserServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public void run(UserServiceConfiguration configuration, Environment environment) {
        // A health check is a good practice
        environment.healthChecks().register("dummy", new com.codahale.metrics.health.HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        // Register with Eureka
        environment.lifecycle().manage(new Managed() {
            private EurekaClient eurekaClient;

            @Override
            public void start() {
                EurekaConfig eurekaConfig = configuration.getEurekaConfig();
                EurekaInstanceConfig instanceConfig = new EurekaInstanceConfig() {
                    @Override
                    public String getAppname() {
                        return eurekaConfig.getServiceName();
                    }

                    @Override
                    public String getNamespace() {
                        return null;
                    }
                    
                    // Implement other methods with default values or from config
                    @Override
                    public String getInstanceId() { return null; }
                    @Override
                    public String getAppGroupName() { return null; }
                    @Override
                    public boolean isInstanceEnabledOnit() { return false; }
                    @Override
                    public int getNonSecurePort() { return 8080; }
                    @Override
                    public int getSecurePort() { return 0; }
                    @Override
                    public boolean isNonSecurePortEnabled() { return true; }
                    @Override
                    public boolean getSecurePortEnabled() { return false; }
                    @Override
                    public int getLeaseRenewalIntervalInSeconds() { return 30; }
                    @Override
                    public int getLeaseExpirationDurationInSeconds() { return 90; }
                    @Override
                    public String getVirtualHostName() { return getAppname(); }
                    @Override
                    public String getSecureVirtualHostName() { return getAppname(); }
                    @Override
                    public String getASGName() { return null; }
                    @Override
                    public String getHostName(boolean refresh) { return "localhost"; }
                    @Override
                    public java.util.Map<String, String> getMetadataMap() { return null; }
                    
                    public com.netflix.appinfo.DataCenterInfo getDataCenterInfo() { return new MyDataCenterInfo(MyDataCenterInfo.Name.MyOwn); }

                    @Override
                    public String getIpAddress() { return "127.0.0.1"; }
                    @Override
                    public String getStatusPageUrlPath() { return "/info"; }
                    @Override
                    public String getStatusPageUrl() { return "http://localhost:8081/info"; }
                    @Override
                    public String getHomePageUrlPath() { return "/"; }
                    @Override
                    public String getHomePageUrl() { return "http://localhost:8080/"; }
                    @Override
                    public String getHealthCheckUrlPath() { return "/healthcheck"; }
                    @Override
                    public String getHealthCheckUrl() { return "http://localhost:8081/healthcheck"; }
                    @Override
                    public String getSecureHealthCheckUrl() { return null; }
                    @Override
                    public String[] getDefaultAddressResolutionOrder() { return new String[0]; }
                };

                EurekaClientConfig clientConfig = new DefaultEurekaClientConfig() {
                    @Override
                    public java.util.List<String> getEurekaServerServiceUrls(String myZone) {
                        return java.util.Collections.singletonList(configuration.getEurekaConfig().getServiceUrl());
                    }
                };

                ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig, (InstanceInfo) null);
                eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
            }

            @Override
            public void stop() {
                if (eurekaClient != null) {
                    eurekaClient.shutdown();
                }
            }
        });
    }
}
