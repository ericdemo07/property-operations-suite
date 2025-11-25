package com.property_operations_suite.api_gateway.factories;

import com.property_operations_suite.api_gateway.filters.AuthenticationRequestProcessor;
import com.property_operations_suite.api_gateway.filters.LoggingRequestProcessor;
import com.property_operations_suite.api_gateway.filters.RequestProcessor;

public class DefaultRequestProcessorFactory implements RequestProcessorFactory{
    @Override
    public RequestProcessor createLoggingProcessor() {
        return new LoggingRequestProcessor();
    }

    @Override
    public RequestProcessor createAuthenticationProcessor() {
        return new AuthenticationRequestProcessor();
    }
}
