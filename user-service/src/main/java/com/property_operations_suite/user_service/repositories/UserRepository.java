package com.property_operations_suite.user_service.repositories;

import com.property_operations_suite.user_service.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save (User user);
    Optional<User> findByEmail (String email);
    Optional<User> findById (UUID id);
}
