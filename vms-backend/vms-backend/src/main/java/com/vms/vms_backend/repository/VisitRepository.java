package com.vms.vms_backend.repository;

import com.vms.vms_backend.entity.Visit;
import com.vms.vms_backend.entity.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    // Total visits for a specific date
    long countByVisitDate(LocalDate visitDate);

    // Count visits by status (PENDING, APPROVED, CHECKED_IN, COMPLETED)
    long countByStatus(VisitStatus status);

    // Count visits by date and status
    long countByVisitDateAndStatus(LocalDate visitDate, VisitStatus status);

    // Optional (useful later)
    List<Visit> findByStatus(VisitStatus status);
}