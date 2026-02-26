package com.vms.vms_backend.repository;

import com.vms.vms_backend.entity.AccessCard;
import com.vms.vms_backend.entity.Visit;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccessCardRepository extends JpaRepository<AccessCard, Long> {

    @Query(value = "SELECT * FROM ACCESS_CARD WHERE CARD_STATUS = :status AND ROWNUM = 1", nativeQuery = true)
    Optional<AccessCard> findAvailableCard(@Param("status") String status);

    Optional<AccessCard> findByAssignedVisit(Visit visit);

    // ✅ Dashboard support
    long countByCardStatus(String status);
}