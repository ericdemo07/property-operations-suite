package com.property_operations_suite.api_gateway.filters;

import javax.servlet.http.HttpServletRequest;

public class LoggingRequestProcessor implements RequestProcessor {
    private RequestProcessor nextProcessor;

    @Override
    public void setNext(RequestProcessor next) {
        this.nextProcessor = next;
    }

    @Override
    public boolean process(HttpServletRequest request) {
        System.out.println("LoggingRequestProcessor: Request received for URI: " + request.getRequestURI());
        if (nextProcessor != null) {
            return nextProcessor.process(request);
        }
        return true;
    }
}
