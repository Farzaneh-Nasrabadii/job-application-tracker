package com.jobtracker.jobapplicationtracker.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    @Query("SELECT j FROM JobApplication j WHERE j.user.id = :userId " +
            "AND (:status IS NULL OR j.status = :status) " +
            "AND (:companyName IS NULL OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :companyName, '%')))")
    Page<JobApplication> findAllByUserIdAndFilters(
            @Param("userId") Long userId,
            @Param("status") ApplicationStatus status,
            @Param("companyName") String companyName,
            Pageable pageable
    );

    Optional<JobApplication> findByIdAndUserId(Long id, Long userId);
}