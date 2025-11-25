package com.property_operations_suite.api_gateway.routing;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import javax.servlet.http.HttpServletRequest;

public class EurekaUrlResolverStrategy implements TargetUrlResolverStrategy {

    private final EurekaClient eurekaClient;

    // For demonstration, this constructor would typically receive a real EurekaClient
    // In our test runner, we'll pass a mock.
    public EurekaUrlResolverStrategy(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public String resolveTargetUrl(String serviceName, HttpServletRequest request) {
        try {
            // This logic mimics the original ProxyServlet's rewriteTarget
            InstanceInfo instance = eurekaClient.getNextServerFromEureka(serviceName, false);
            if (instance != null) {
                // Assuming request.getRequestURI() contains the full path after the gateway prefix
                // e.g., "/user-service/api/v1/users"
                // instance.getHomePageUrl() might be "http://localhost:8080"
                // The result should be "http://localhost:8080/user-service/api/v1/users"
                return instance.getHomePageUrl() + request.getRequestURI();
            }
        } catch (Exception e) {
            System.err.println("Error resolving service " + serviceName + " with Eureka: " + e.getMessage());
            // Log the exception properly in a real application
        }
        return null; // Service not found or error
    }
}
