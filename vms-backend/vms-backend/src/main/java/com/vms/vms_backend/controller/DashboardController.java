package com.vms.vms_backend.controller;

import com.vms.vms_backend.dto.DashboardResponseDTO;
import com.vms.vms_backend.entity.VisitStatus;
import com.vms.vms_backend.repository.AccessCardRepository;
import com.vms.vms_backend.repository.VisitRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final VisitRepository visitRepository;
    private final AccessCardRepository accessCardRepository;

    public DashboardController(VisitRepository visitRepository,
                               AccessCardRepository accessCardRepository) {
        this.visitRepository = visitRepository;
        this.accessCardRepository = accessCardRepository;
    }

    @GetMapping("/stats")
    public DashboardResponseDTO getDashboardStats() {

        LocalDate today = LocalDate.now();

        long totalToday = visitRepository.countByVisitDate(today);
        long pending = visitRepository.countByStatus(VisitStatus.PENDING);
        long active = visitRepository.countByStatus(VisitStatus.CHECKED_IN);
        long completedToday =
                visitRepository.countByVisitDateAndStatus(today, VisitStatus.COMPLETED);
        long availableCards = accessCardRepository.countByCardStatus("AVAILABLE");

        return new DashboardResponseDTO(
                totalToday,
                pending,
                active,
                completedToday,
                availableCards
        );
    }
}