package com.jobtracker.jobapplicationtracker.application;

import com.jobtracker.jobapplicationtracker.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getAllApplications(User user, JobApplicationFilter filter, Pageable pageable) {
        return repository.findAllByUserIdAndFilters(user.getId(), filter.status(), filter.companyName(), pageable)
                .map(this::toResponse);
    }

    @Transactional
    public JobApplicationResponse createApplication(User user, JobApplicationRequest request) {
        JobApplication application = new JobApplication(
                request.companyName(),
                request.jobTitle(),
                request.jobUrl(),
                request.location(),
                request.salary(),
                request.status(),
                request.notes(),
                request.appliedDate(),
                user
        );
        return toResponse(repository.save(application));
    }

    @Transactional(readOnly = true)
    public JobApplicationResponse getApplicationById(User user, Long id) {
        return repository.findByIdAndUserId(id, user.getId())
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + id));
    }

    @Transactional
    public JobApplicationResponse updateApplication(User user, Long id, JobApplicationRequest request) {
        JobApplication application = repository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + id));

        application.setCompanyName(request.companyName());
        application.setJobTitle(request.jobTitle());
        application.setJobUrl(request.jobUrl());
        application.setLocation(request.location());
        application.setSalary(request.salary());
        if (request.status() != null) {
            application.setStatus(request.status());
        }
        application.setNotes(request.notes());
        if (request.appliedDate() != null) {
            application.setAppliedDate(request.appliedDate());
        }

        return toResponse(repository.save(application));
    }

    @Transactional
    public void deleteApplication(User user, Long id) {
        JobApplication application = repository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + id));
        repository.delete(application);
    }

    private JobApplicationResponse toResponse(JobApplication app) {
        return new JobApplicationResponse(
                app.getId(),
                app.getCompanyName(),
                app.getJobTitle(),
                app.getJobUrl(),
                app.getLocation(),
                app.getSalary(),
                app.getStatus(),
                app.getNotes(),
                app.getAppliedDate(),
                app.getCreatedAt(),
                app.getUpdatedAt()
        );
    }
}