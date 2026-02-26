package com.vms.vms_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "VISIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_seq_gen")
    @SequenceGenerator(name = "visit_seq_gen", sequenceName = "VISIT_SEQ", allocationSize = 1)
    @Column(name = "VISIT_ID")
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "VISITOR_ID", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private Employee employee;

    @Column(name = "PURPOSE_OF_VISIT")
    private String purposeOfVisit;

    @Column(name = "VISIT_DATE")
    private LocalDate visitDate;

    @Column(name = "CHECK_IN_TIME")
    private LocalDateTime checkInTime;

    @Column(name = "CHECK_OUT_TIME")
    private LocalDateTime checkOutTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private VisitStatus status;

    @Column(name = "APPROVAL_TIME")
    private LocalDateTime approvalTime;

    @ManyToOne
    @JoinColumn(name = "APPROVED_BY")
    private Employee approvedBy;

    @Column(name = "REJECTION_REASON")
    private String rejectionReason;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}