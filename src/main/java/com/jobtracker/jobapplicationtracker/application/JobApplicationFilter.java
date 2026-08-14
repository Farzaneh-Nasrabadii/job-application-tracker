package com.jobtracker.jobapplicationtracker.application;

public record JobApplicationFilter(
        ApplicationStatus status,
        String companyName
) {}