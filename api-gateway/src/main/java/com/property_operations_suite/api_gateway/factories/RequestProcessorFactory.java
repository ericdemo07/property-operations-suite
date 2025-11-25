package com.property_operations_suite.api_gateway.factories;

import com.property_operations_suite.api_gateway.filters.RequestProcessor;

public interface RequestProcessorFactory {
    RequestProcessor createLoggingProcessor();

    RequestProcessor createAuthenticationProcessor();
}
