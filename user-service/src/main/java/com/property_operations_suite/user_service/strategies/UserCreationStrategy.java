package com.property_operations_suite.user_service.strategies;

import com.property_operations_suite.user_service.model.User;

public interface UserCreationStrategy {
    public User createUser(User user);
}
