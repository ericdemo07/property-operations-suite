package com.property_operations_suite.api_gateway.demo;

import com.property_operations_suite.api_gateway.factories.DefaultRequestProcessorFactory;
import com.property_operations_suite.api_gateway.factories.RequestProcessorFactory;
import com.property_operations_suite.api_gateway.filters.RequestProcessor;
import com.property_operations_suite.api_gateway.routing.CachedUrlResolverDecorator;
import com.property_operations_suite.api_gateway.routing.EurekaUrlResolverStrategy;
import com.property_operations_suite.api_gateway.routing.TargetUrlResolverStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ApiGatewayTestRunner {

    public static void main(String[] args) {

        RequestProcessorFactory processorFactory = new DefaultRequestProcessorFactory();
        RequestProcessor loggingProcessor = processorFactory.createLoggingProcessor();
        RequestProcessor authenticationProcessor = processorFactory.createAuthenticationProcessor();

        loggingProcessor.setNext(authenticationProcessor);

        // --- Strategy Pattern Setup ---
        MockEurekaClient mockEurekaClient = new MockEurekaClient();
        // Register mock instances for demonstration using the simplified registerInstance
        mockEurekaClient.registerInstance("user-service", "http://localhost:8081");
        mockEurekaClient.registerInstance("booking-service", "http://localhost:8082");
        mockEurekaClient.registerInstance("inventory-service", "http://localhost:8083");

        TargetUrlResolverStrategy eurekaResolver = new CachedUrlResolverDecorator(new EurekaUrlResolverStrategy(mockEurekaClient));

        System.out.println("--- Demonstrating Chain of Responsibility and Strategy Pattern ---");

        // Scenario 1: Authenticated request for user-service
        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("X-Auth-Token", "secret-token");
        HttpServletRequest userRequest = new MockHttpServletRequest("/user-service/api/users/123", authHeaders);

        System.out.println("\n--- Processing /user-service/api/users/123 (Authenticated) ---");
        boolean chainResult1 = loggingProcessor.process(userRequest);
        if (chainResult1) {
            String path = userRequest.getRequestURI();
            String serviceName = path.substring(1).split("/")[0]; // "user-service"
            String targetUrl = eurekaResolver.resolveTargetUrl(serviceName, userRequest);
            System.out.println("Strategy resolved target URL: " + targetUrl);
        } else {
            System.out.println("Chain stopped. No target URL resolved.");
        }

        // Scenario 2: Unauthenticated request for booking-service
        Map<String, String> noAuthHeaders = new HashMap<>();
        HttpServletRequest bookingRequest = new MockHttpServletRequest("/booking-service/api/bookings",
                noAuthHeaders);

        System.out.println("\n--- Processing /booking-service/api/bookings (Unauthenticated) ---");
        boolean chainResult2 = loggingProcessor.process(bookingRequest);
        if (chainResult2) {
            String path = bookingRequest.getRequestURI();
            String serviceName = path.substring(1).split("/")[0]; // "booking-service"
            String targetUrl = eurekaResolver.resolveTargetUrl(serviceName, bookingRequest);
            System.out.println("Strategy resolved target URL: " + targetUrl);
        } else {
            System.out.println("Chain stopped. No target URL resolved.");
        }

        // Scenario 3: Authenticated request for a service not found in Eureka
        Map<String, String> authHeaders2 = new HashMap<>();
        authHeaders2.put("X-Auth-Token", "secret-token");
        HttpServletRequest unknownServiceRequest = new MockHttpServletRequest("/unknown-service/api/data",
                authHeaders2);

        System.out.println("\n--- Processing /unknown-service/api/data (Authenticated, Unknown Service) ---");
        boolean chainResult3 = loggingProcessor.process(unknownServiceRequest);
        if (chainResult3) {
            String path = unknownServiceRequest.getRequestURI();
            String serviceName = path.substring(1).split("/")[0]; // "unknown-service"
            String targetUrl = eurekaResolver.resolveTargetUrl(serviceName, unknownServiceRequest);
            System.out.println("Strategy resolved target URL: " + targetUrl);
        } else {
            System.out.println("Chain stopped. No target URL resolved.");
        }
    }
}
