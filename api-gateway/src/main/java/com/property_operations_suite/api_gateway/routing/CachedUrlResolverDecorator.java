package com.property_operations_suite.api_gateway.routing;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedUrlResolverDecorator implements TargetUrlResolverStrategy {
    private final Map<String, String> cache;
    private final TargetUrlResolverStrategy decoratedStrategy;

    public CachedUrlResolverDecorator(TargetUrlResolverStrategy decoratedStrategy) {
        this.decoratedStrategy = decoratedStrategy;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public String resolveTargetUrl(String serviceName, HttpServletRequest request) {
        if (cache.containsKey(serviceName)) {
            System.out.println("CachedUrlResolverDecorator: Returning URL for '" + serviceName + "' from cache.");
            return cache.get(serviceName);
        }
        System.out.println("CachedUrlResolverDecorator: Resolving URL for '" + serviceName + "' using decorated strategy.");
        String resolvedUrl = decoratedStrategy.resolveTargetUrl(serviceName, request);
        if (resolvedUrl != null) {
            cache.put(serviceName, resolvedUrl);
        }
        return resolvedUrl;

    }
}
