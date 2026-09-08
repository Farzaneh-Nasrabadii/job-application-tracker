package com.jobtracker.jobapplicationtracker.application;
import com.jobtracker.jobapplicationtracker.user.User; import org.springframework.data.domain.Page; import org.springframework.data.domain.Pageable; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.security.core.annotation.AuthenticationPrincipal; import org.springframework.web.bind.annotation.*; import org.springframework.web.multipart.MultipartFile;
@RestController @RequestMapping("/api/v1/applications") public class JobApplicationController {
    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createApplication(
            @AuthenticationPrincipal User user,
            @RequestBody JobApplicationRequest request) {
        JobApplicationResponse response = service.createApplication(user, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<JobApplicationResponse>> getAllApplications(
            @AuthenticationPrincipal User user,
            JobApplicationFilter filter,
            Pageable pageable) {
        Page<JobApplicationResponse> responses = service.getAllApplications(user, filter, pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id) {
        JobApplicationResponse response = service.getApplicationById(user, id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<JobApplicationResponse> uploadResume(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        JobApplicationResponse response = service.uploadResume(user, id, file);
        return ResponseEntity.ok(response);
    }
}