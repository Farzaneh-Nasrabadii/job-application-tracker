package com.jobtracker.jobapplicationtracker.application;

import com.jobtracker.jobapplicationtracker.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<JobApplicationResponse>> getAll(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String companyName,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        JobApplicationFilter filter = new JobApplicationFilter(status, companyName);
        return ResponseEntity.ok(service.getAllApplications(user, filter, pageable));
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> create(
            @AuthenticationPrincipal User user,
            @RequestBody JobApplicationRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createApplication(user, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getApplicationById(user, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody JobApplicationRequest request
    ) {
        return ResponseEntity.ok(service.updateApplication(user, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        service.deleteApplication(user, id);
        return ResponseEntity.noContent().build();
    }
}