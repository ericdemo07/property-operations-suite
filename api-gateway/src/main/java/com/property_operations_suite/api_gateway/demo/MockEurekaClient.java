package com.property_operations_suite.api_gateway.demo;

import com.netflix.appinfo.*;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.EurekaEventListener;
import com.netflix.discovery.shared.Applications;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MockEurekaClient implements EurekaClient {
    private final Map<String, InstanceInfo> registeredInstances = new HashMap<>();

    public void registerInstance(String serviceName, String homePageUrl) {
        InstanceInfo instance = InstanceInfo.Builder.newBuilder()
                .setAppName(serviceName.toUpperCase()) // Eureka typically uses uppercase app names
                .setInstanceId(serviceName + "-mock-instance")
                .setHostName("localhost")
                .setIPAddr("127.0.0.1")
                .setPort(8080) // A dummy port
                .enablePort(InstanceInfo.PortType.UNSECURE, true)
                .setVIPAddress(serviceName)
                .setHomePageUrl(homePageUrl, homePageUrl) // Set the specific home page URL
                .setDataCenterInfo(new MyDataCenterInfo(MyDataCenterInfo.Name.MyOwn))
                .setStatus(InstanceInfo.InstanceStatus.UP)
                .build();

        registeredInstances.put(serviceName, instance);
    }

    @Override
    public InstanceInfo getNextServerFromEureka(String serviceId, boolean secure) {
        // Simulate Eureka's behavior: return an instance for the serviceId
        return registeredInstances.get(serviceId);
    }

    // Other EurekaClient methods can be left unimplemented for this demo
    @Override
    public void shutdown() {
    }

    @Override
    public EurekaClientConfig getEurekaClientConfig() {
        return null;
    }

    @Override
    public ApplicationInfoManager getApplicationInfoManager() {
        return null;
    }

    @Override
    public Applications getApplications() {
        return null;
    }

    @Override
    public List<InstanceInfo> getInstancesById(String s) {
        return List.of();
    }

    @Override
    public Applications getApplicationsForARegion(@Nullable String s) {
        return null;
    }

    @Override
    public Applications getApplications(String appName) {
        return null;
    }

    @Override
    public List<InstanceInfo> getInstancesByVipAddress(String vipAddress, boolean secure) {
        return null;
    }

    @Override
    public List<InstanceInfo> getInstancesByVipAddress(String vipAddress, boolean secure, String region) {
        return
                null;
    }

    @Override
    public List<InstanceInfo> getInstancesByVipAddressAndAppName(String vipAddress, String appName, boolean secure) {
        return null;
    }

    @Override
    public Set<String> getAllKnownRegions() {
        return Set.of();
    }

    @Override
    public InstanceInfo.InstanceStatus getInstanceRemoteStatus() {
        return null;
    }

    @Override
    public List<String> getDiscoveryServiceUrls(String s) {
        return List.of();
    }

    @Override
    public List<String> getServiceUrlsFromConfig(String s, boolean b) {
        return List.of();
    }

    @Override
    public List<String> getServiceUrlsFromDNS(String s, boolean b) {
        return List.of();
    }

    @Override
    public void registerHealthCheckCallback(HealthCheckCallback healthCheckCallback) {

    }

    @Override
    public void registerHealthCheck(HealthCheckHandler healthCheckHandler) {
    }

    @Override
    public void registerEventListener(EurekaEventListener eurekaEventListener) {

    }

    @Override
    public boolean unregisterEventListener(EurekaEventListener eurekaEventListener) {
        return false;
    }

    @Override
    public HealthCheckHandler getHealthCheckHandler() {
        return null;
    }


    @Override
    public com.netflix.discovery.shared.Application getApplication(String appName) {
        return null;
    }


}
