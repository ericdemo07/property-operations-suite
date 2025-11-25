package com.property_operations_suite.user_service.repositories;

import com.property_operations_suite.user_service.model.User;

import java.util.Optional;
import java.util.UUID;

public class LoggingUserRepositoryDecorator implements UserRepository {
    private final UserRepository decoratedUserRepository;

    public LoggingUserRepositoryDecorator(UserRepository userRepository) {
        this.decoratedUserRepository = userRepository;
    }

    @Override
    public User save(User user) {
        System.out.println("LoggingUserRepositoryDecorator save");
        return decoratedUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("LoggingUserRepositoryDecorator findByEmail");
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(UUID id) {
        System.out.println("LoggingUserRepositoryDecorator findById");
        return decoratedUserRepository.findById(id);
    }
}
