package com.property_operations_suite.user_service.service;

import com.property_operations_suite.user_service.model.User;
import com.property_operations_suite.user_service.repositories.UserRepository;
import com.property_operations_suite.user_service.strategies.UserCreationStrategy;

public class UserService {
    private final UserCreationStrategy userCreationStrategy;
    private final UserRepository userRepository;

    public UserService(UserCreationStrategy userCreationStrategy, UserRepository userRepository) {
        this.userCreationStrategy = userCreationStrategy;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userCreationStrategy == null) {
            System.err.println("UserCreationStrategy is null");
            return null;
        }
        User user1 = this.userCreationStrategy.createUser(user);
        return this.userRepository.save(user1);
    }
}
