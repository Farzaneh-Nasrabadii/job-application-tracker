package com.jobtracker.jobapplicationtracker.application;

import com.jobtracker.jobapplicationtracker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    private User testUser;
    private JobApplication testApplication;

    @BeforeEach
    void setUp() {
        testUser = new User("Farzaneh", "farzaneh@example.com", "password123", User.Role.USER);
        testApplication = new JobApplication(
                "Google",
                "Software Engineer",
                "https://careers.google.com",
                "Germany",
                "80000 EUR",
                ApplicationStatus.APPLIED,
                "Applied via portal",
                LocalDate.now(),
                testUser
        );
    }

    @Test
    void createApplication_ShouldSaveAndReturnResponse() {
        JobApplicationRequest request = new JobApplicationRequest(
                "Google",
                "Software Engineer",
                "https://careers.google.com",
                "Germany",
                "80000 EUR",
                ApplicationStatus.APPLIED,
                "Applied via portal",
                LocalDate.now()
        );

        when(repository.save(any(JobApplication.class))).thenReturn(testApplication);

        JobApplicationResponse response = service.createApplication(testUser, request);

        assertNotNull(response);
        assertEquals("Google", response.companyName());
        assertEquals("Software Engineer", response.jobTitle());
        verify(repository, times(1)).save(any(JobApplication.class));
    }

    @Test
    void getApplicationById_ShouldReturnApplication_WhenExists() {
        when(repository.findByIdAndUserId(1L, testUser.getId())).thenReturn(Optional.of(testApplication));

        JobApplicationResponse response = service.getApplicationById(testUser, 1L);

        assertNotNull(response);
        assertEquals("Google", response.companyName());
        verify(repository, times(1)).findByIdAndUserId(1L, testUser.getId());
    }
}