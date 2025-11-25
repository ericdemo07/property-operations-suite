package com.property_operations_suite.user_service.commands;

import com.property_operations_suite.user_service.model.User;
import com.property_operations_suite.user_service.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateUserCommand implements UserCommand {
    private final String firstName;
    private final UserService userService;
    private final String email;

    public CreateUserCommand(UserService userService, String firstName, String email) {
        this.userService = userService;
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public void execute() {
        User user = User.newBuilder()
                .withId(UUID.randomUUID())
                .firstName(this.firstName)
                .email(this.email)
                .createdAt(LocalDateTime.now())
                .build();

        userService.createUser(user);
        System.out.println("Command executed: Created user with email: " + this.email);
    }
}
