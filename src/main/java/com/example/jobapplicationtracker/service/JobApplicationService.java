package com.example.jobapplicationtracker.service;

import com.example.jobapplicationtracker.dto.JobApplicationRequest;
import com.example.jobapplicationtracker.dto.JobApplicationResponse;
import com.example.jobapplicationtracker.model.ApplicationStatus;
import com.example.jobapplicationtracker.model.JobApplication;
import com.example.jobapplicationtracker.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    @Transactional
    public JobApplicationResponse createApplication(JobApplicationRequest request) {
        JobApplication application = mapToEntity(request);
        if (application.getApplicationDate() == null) {
            application.setApplicationDate(LocalDate.now());
        }
        JobApplication saved = repository.save(application);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getAllApplications(String company, ApplicationStatus status, Pageable pageable) {
        Page<JobApplication> page;
        if (company != null && !company.isBlank()) {
            page = repository.findByCompanyNameContainingIgnoreCase(company, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return page.map(this::mapToResponse);
    }
@Transactional(readOnly = true)
    public JobApplicationResponse getApplicationById(Long id) {
        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found with id: " + id));
        return mapToResponse(application);
    }

    @Transactional
    public JobApplicationResponse updateApplication(Long id, JobApplicationRequest request) {
        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found with id: " + id));

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setJobUrl(request.getJobUrl());
        application.setLocation(request.getLocation());
        application.setSalary(request.getSalary());
        application.setStatus(request.getStatus());
        if (request.getApplicationDate() != null) {
            application.setApplicationDate(request.getApplicationDate());
        }
        application.setNotes(request.getNotes());

        return mapToResponse(repository.save(application));
    }
@Transactional
    public void deleteApplication(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Job application not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private JobApplication mapToEntity(JobApplicationRequest request) {
        JobApplication entity = new JobApplication();
        entity.setCompanyName(request.getCompanyName());
        entity.setJobTitle(request.getJobTitle());
        entity.setJobUrl(request.getJobUrl());
        entity.setLocation(request.getLocation());
        entity.setSalary(request.getSalary());
        entity.setStatus(request.getStatus());
        entity.setApplicationDate(request.getApplicationDate());
        entity.setNotes(request.getNotes());
        return entity;
    }
private JobApplicationResponse mapToResponse(JobApplication entity) {
        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(entity.getId());
        response.setCompanyName(entity.getCompanyName());
        response.setJobTitle(entity.getJobTitle());
        response.setJobUrl(entity.getJobUrl());
        response.setLocation(entity.getLocation());
        response.setSalary(entity.getSalary());
        response.setStatus(entity.getStatus());
        response.setApplicationDate(entity.getApplicationDate());
        response.setNotes(entity.getNotes());
        return response;
    }
}
