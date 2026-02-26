package com.vms.vms_backend.dto;

import com.vms.vms_backend.entity.VisitStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VisitResponseDTO {

    private Long visitId;
    private String visitorName;
    private String employeeName;
    private String purpose;
    private VisitStatus status;
    private LocalDate visitDate;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public VisitResponseDTO(Long visitId,
                            String visitorName,
                            String employeeName,
                            String purpose,
                            VisitStatus status,
                            LocalDate visitDate,
                            LocalDateTime checkInTime,
                            LocalDateTime checkOutTime) {
        this.visitId = visitId;
        this.visitorName = visitorName;
        this.employeeName = employeeName;
        this.purpose = purpose;
        this.status = status;
        this.visitDate = visitDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public Long getVisitId() { return visitId; }
    public String getVisitorName() { return visitorName; }
    public String getEmployeeName() { return employeeName; }
    public String getPurpose() { return purpose; }
    public VisitStatus getStatus() { return status; }
    public LocalDate getVisitDate() { return visitDate; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public LocalDateTime getCheckOutTime() { return checkOutTime; }
}