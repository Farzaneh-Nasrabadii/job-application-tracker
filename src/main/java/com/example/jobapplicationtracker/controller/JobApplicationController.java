package com.example.jobapplicationtracker.controller;

import com.example.jobapplicationtracker.dto.JobApplicationRequest;
import com.example.jobapplicationtracker.dto.JobApplicationResponse;
import com.example.jobapplicationtracker.model.ApplicationStatus;
import com.example.jobapplicationtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class JobApplicationController {
private final JobApplicationService service;

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createApplication(@Valid @RequestBody JobApplicationRequest request) {
        return new ResponseEntity<>(service.createApplication(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<JobApplicationResponse>> getAllApplications(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) ApplicationStatus status,
            @PageableDefault(size = 10, sort = "applicationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getAllApplications(company, status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplicationById(id));
    }
@PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequest request) {
        return ResponseEntity.ok(service.updateApplication(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
