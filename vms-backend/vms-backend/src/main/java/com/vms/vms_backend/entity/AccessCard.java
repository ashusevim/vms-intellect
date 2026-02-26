package com.vms.vms_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCESS_CARD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_gen")
    @SequenceGenerator(
            name = "card_seq_gen",
            sequenceName = "CARD_SEQ",
            allocationSize = 1
    )
    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "CARD_NUMBER", unique = true)
    private String cardNumber;

    @Column(name = "QR_CODE_VALUE")
    private String qrCodeValue;

    @Column(name = "ACCESS_LEVEL")
    private String accessLevel;

    @Column(name = "CARD_STATUS")
    private String cardStatus;   

    @OneToOne
    @JoinColumn(name = "ASSIGNED_VISIT_ID")
    private Visit assignedVisit;

    @Column(name = "ISSUED_TIME")
    private LocalDateTime issuedTime;

    @Column(name = "EXPIRY_TIME")
    private LocalDateTime expiryTime;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}