package com.jobtracker.jobapplicationtracker.application;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobApplicationResponse(
        Long id,
        String companyName,
        String jobTitle,
        String jobUrl,
        String location,
        String salary,
        ApplicationStatus status,
        String notes,
        LocalDate appliedDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}