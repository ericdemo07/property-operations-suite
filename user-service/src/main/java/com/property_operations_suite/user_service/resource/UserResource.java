package com.property_operations_suite.user_service.resource;

import com.property_operations_suite.user_service.commands.CreateUserCommand;
import com.property_operations_suite.user_service.commands.UserCommand;
import com.property_operations_suite.user_service.service.UserService;

public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    public void createNewUser(String firstName, String email) {

        UserCommand userCommand = new CreateUserCommand(this.userService, firstName, email);
        userCommand.execute();

    }
}