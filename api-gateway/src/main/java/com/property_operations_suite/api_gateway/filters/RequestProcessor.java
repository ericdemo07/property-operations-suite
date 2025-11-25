package com.property_operations_suite.api_gateway.filters;

import javax.servlet.http.HttpServletRequest;

public interface RequestProcessor {
    void setNext(RequestProcessor next);

    boolean process(HttpServletRequest request);
}
