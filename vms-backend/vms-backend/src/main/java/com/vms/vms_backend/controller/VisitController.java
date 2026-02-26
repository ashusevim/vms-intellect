package com.vms.vms_backend.controller;

import com.vms.vms_backend.dto.VisitResponseDTO;
import com.vms.vms_backend.entity.*;
import com.vms.vms_backend.exception.BadRequestException;
import com.vms.vms_backend.exception.ResourceNotFoundException;
import com.vms.vms_backend.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final EmployeeRepository employeeRepository;
    private final AccessCardRepository accessCardRepository;

    public VisitController(VisitRepository visitRepository,
                           VisitorRepository visitorRepository,
                           EmployeeRepository employeeRepository,
                           AccessCardRepository accessCardRepository) {
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
        this.employeeRepository = employeeRepository;
        this.accessCardRepository = accessCardRepository;
    }

    // ✅ DTO Mapper
    private VisitResponseDTO mapToDTO(Visit visit) {
        return new VisitResponseDTO(
                visit.getVisitId(),
                visit.getVisitor().getFirstName() + " " + visit.getVisitor().getLastName(),
                visit.getEmployee().getFirstName() + " " + visit.getEmployee().getLastName(),
                visit.getPurposeOfVisit(),
                visit.getStatus(),
                visit.getVisitDate(),
                visit.getCheckInTime(),
                visit.getCheckOutTime()
        );
    }

    // ✅ CREATE VISIT
    @PostMapping
    public VisitResponseDTO createVisit(@RequestParam Long visitorId,
                                        @RequestParam Long employeeId,
                                        @RequestParam String purpose) {

        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Visit visit = new Visit();
        visit.setVisitor(visitor);
        visit.setEmployee(employee);
        visit.setPurposeOfVisit(purpose);
        visit.setVisitDate(LocalDate.now());
        visit.setStatus(VisitStatus.PENDING);

        Visit saved = visitRepository.save(visit);
        return mapToDTO(saved);
    }

    // ✅ APPROVE VISIT
    @PutMapping("/{visitId}/approve")
    public VisitResponseDTO approveVisit(@PathVariable Long visitId,
                                         @RequestParam Long adminId) {

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found"));

        if (visit.getStatus() != VisitStatus.PENDING) {
            throw new BadRequestException("Only PENDING visits can be approved");
        }

        Employee admin = employeeRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        AccessCard card = accessCardRepository
                .findAvailableCard("AVAILABLE")
                .orElseThrow(() -> new BadRequestException("No available access cards"));

        visit.setStatus(VisitStatus.APPROVED);
        visit.setApprovalTime(LocalDateTime.now());
        visit.setApprovedBy(admin);

        card.setCardStatus("ASSIGNED");
        card.setAssignedVisit(visit);
        card.setIssuedTime(LocalDateTime.now());
        card.setExpiryTime(LocalDateTime.now().plusHours(8));

        accessCardRepository.save(card);

        Visit updated = visitRepository.save(visit);
        return mapToDTO(updated);
    }

    // ✅ CHECK-IN
    @PutMapping("/{visitId}/checkin")
    public VisitResponseDTO checkIn(@PathVariable Long visitId) {

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found"));

        if (visit.getStatus() != VisitStatus.APPROVED) {
            throw new BadRequestException("Visit is not approved");
        }

        visit.setCheckInTime(LocalDateTime.now());
        visit.setStatus(VisitStatus.CHECKED_IN);

        Visit updated = visitRepository.save(visit);
        return mapToDTO(updated);
    }

    // ✅ CHECK-OUT
    @PutMapping("/{visitId}/checkout")
    public VisitResponseDTO checkOut(@PathVariable Long visitId) {

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found"));

        if (visit.getStatus() != VisitStatus.CHECKED_IN) {
            throw new BadRequestException("Visitor is not checked in");
        }

        visit.setCheckOutTime(LocalDateTime.now());
        visit.setStatus(VisitStatus.COMPLETED);

        AccessCard card = accessCardRepository.findByAssignedVisit(visit)
                .orElseThrow(() -> new BadRequestException("No card assigned to this visit"));

        card.setCardStatus("AVAILABLE");
        card.setAssignedVisit(null);
        card.setIssuedTime(null);
        card.setExpiryTime(null);

        accessCardRepository.save(card);

        Visit updated = visitRepository.save(visit);
        return mapToDTO(updated);
    }
}