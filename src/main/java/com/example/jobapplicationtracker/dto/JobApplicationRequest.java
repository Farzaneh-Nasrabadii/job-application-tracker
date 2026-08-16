package com.example.jobapplicationtracker.dto;

import com.example.jobapplicationtracker.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobApplicationRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    private String jobUrl;

    private String location;

    private Double salary;
@NotNull(message = "Application status is required")
    private ApplicationStatus status;

    private LocalDate applicationDate;

    private String notes;
}
