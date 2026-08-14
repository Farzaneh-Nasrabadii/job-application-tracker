package com.jobtracker.jobapplicationtracker.application;

import java.time.LocalDate;

public record JobApplicationRequest(
        String companyName,
        String jobTitle,
        String jobUrl,
        String location,
        String salary,
        ApplicationStatus status,
        String notes,
        LocalDate appliedDate
) {}