package com.property_operations_suite.user_service.strategies;

import com.property_operations_suite.user_service.model.User;

public class DefaultUserCreationStrategy implements UserCreationStrategy{
    @Override
    public User createUser(User user) {
        System.out.println("Creating default user");
        return user;
    }
}
