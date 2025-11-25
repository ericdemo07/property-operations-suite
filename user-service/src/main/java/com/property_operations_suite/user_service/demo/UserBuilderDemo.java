package com.property_operations_suite.user_service.demo;

import com.property_operations_suite.user_service.model.User;

import java.time.LocalDateTime;

public class UserBuilderDemo {
    public static void main(String... args) {
        User user = User.newBuilder()
                .email("doom@gmail.com")
                .firstName("Daniel")
                .address("delhi")
                .countryCode("IN")
                .createdAt(LocalDateTime.now())
                .build();

        System.out.println(user);
    }
}
