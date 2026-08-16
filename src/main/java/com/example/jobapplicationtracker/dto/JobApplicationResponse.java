package com.example.jobapplicationtracker.dto;

import com.example.jobapplicationtracker.model.ApplicationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobApplicationResponse {
private Long id;
    private String companyName;
    private String jobTitle;
    private String jobUrl;
    private String location;
    private Double salary;
    private ApplicationStatus status;
    private LocalDate applicationDate;
    private String notes;
}
