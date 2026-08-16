package com.example.jobapplicationtracker.repository;

import com.example.jobapplicationtracker.model.JobApplication;
import com.example.jobapplicationtracker.model.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication> {
    
    Page<JobApplication> findByStatus(ApplicationStatus status, Pageable pageable);
    
    Page<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName, Pageable pageable);
}
