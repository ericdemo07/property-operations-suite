package com.property_operations_suite.api_gateway.routing;

import javax.servlet.http.HttpServletRequest;

public interface TargetUrlResolverStrategy {
    /**
     * Resolves the full target URL for a given service and request.
     *
     * @param serviceName The name of the service to resolve (e.g., "user-service").
     * @param request     The incoming HttpServletRequest, which might contain additional path info.
     * @return The complete target URL, or null if the service cannot be resolved.
     */
    String resolveTargetUrl(String serviceName, HttpServletRequest request);
}
