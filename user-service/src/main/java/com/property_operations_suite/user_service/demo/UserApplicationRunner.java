package com.property_operations_suite.user_service.demo;

import com.property_operations_suite.user_service.factories.DefaultUserStrategyFactory;
import com.property_operations_suite.user_service.factories.UserStrategyFactory;
import com.property_operations_suite.user_service.repositories.InMemoryUserRepository;
import com.property_operations_suite.user_service.repositories.LoggingUserRepositoryDecorator;
import com.property_operations_suite.user_service.repositories.UserRepository;
import com.property_operations_suite.user_service.resource.UserResource;
import com.property_operations_suite.user_service.service.UserService;
import com.property_operations_suite.user_service.strategies.UserCreationStrategy;

public class UserApplicationRunner {
    public static void main(String[] args) {

        UserStrategyFactory userStrategyFactory = new DefaultUserStrategyFactory();

        UserCreationStrategy defaultStrategy = userStrategyFactory.createUserCreationStrategy();


        UserRepository userRepository = new LoggingUserRepositoryDecorator(new InMemoryUserRepository());

        UserService userService = new UserService(defaultStrategy, userRepository);
        UserResource userResource = new UserResource(userService);

        userResource.createNewUser("John", "john.doe@example.com");
    }
}
