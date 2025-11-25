package com.property_operations_suite.user_service.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String passwordHash;
    private final String phone;
    private final String address;
    private final String city;
    private final String state;
    private final String zip;
    private final String countryCode;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.passwordHash = builder.passwordHash;
        this.phone = builder.phone;
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
        this.countryCode = builder.countryCode;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String toString() {
        return "User: id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", passwordHash=" + passwordHash
                + ", phone=" + phone + ", address=" + address + ", city=" + city
                + ", state=" + state + ", zip=" + zip + ", countryCode=" + countryCode
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt;
    }

    public static class Builder {

        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private String passwordHash;
        private String phone;
        private String address;
        private String city;
        private String state;
        private String zip;
        private String countryCode;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            if (this.firstName == null) {
                throw new IllegalArgumentException("firstName is required");
            }
            return new User(this);
        }
    }
}
