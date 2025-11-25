package com.property_operations_suite.api_gateway.filters;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationRequestProcessor implements RequestProcessor {
    private RequestProcessor nextProcessor;

    @Override
    public void setNext(RequestProcessor next) {
        this.nextProcessor = next;
    }

    @Override
    public boolean process(HttpServletRequest request) {
        System.out.println("AuthenticationRequestProcessor: Checking authentication...");
        // Simulate authentication: For demonstration, require a specific header
        if ("secret-token".equals(request.getHeader("X-Auth-Token"))) {
            System.out.println("AuthenticationRequestProcessor: Authentication successful.");
            if (nextProcessor != null) {
                return nextProcessor.process(request);
            }
            return true; // End of chain, continue processing
        }
        System.out.println("AuthenticationRequestProcessor: Authentication failed. Missing or invalid 'X-Auth-Token'.");
        return false; // Stop the chain, request is not authenticated
    }
}
