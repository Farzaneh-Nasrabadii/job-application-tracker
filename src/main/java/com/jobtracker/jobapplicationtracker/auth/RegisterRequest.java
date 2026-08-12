package com.jobtracker.jobapplicationtracker.auth;

public record RegisterRequest(String email, String password, String fullName) {}
