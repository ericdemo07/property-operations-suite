package com.property_operations_suite.user_service.factories;

import com.property_operations_suite.user_service.strategies.UserCreationStrategy;

public interface UserStrategyFactory {
    UserCreationStrategy createUserCreationStrategy();
}
