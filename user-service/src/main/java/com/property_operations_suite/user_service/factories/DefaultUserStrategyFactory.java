package com.property_operations_suite.user_service.factories;

import com.property_operations_suite.user_service.strategies.DefaultUserCreationStrategy;
import com.property_operations_suite.user_service.strategies.UserCreationStrategy;

public class DefaultUserStrategyFactory implements UserStrategyFactory{
    @Override
    public UserCreationStrategy createUserCreationStrategy() {
        return new DefaultUserCreationStrategy();
    }
}
