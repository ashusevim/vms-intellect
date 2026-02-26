package com.vms.vms_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "VISITOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_seq_gen")
    @SequenceGenerator(
            name = "visitor_seq_gen",
            sequenceName = "VISITOR_SEQ",
            allocationSize = 1
    )
    @Column(name = "VISITOR_ID")
    private Long visitorId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ID_PROOF_TYPE")
    private String idProofType;

    @Column(name = "ID_PROOF_NUMBER")
    private String idProofNumber;

    @Column(name = "BLACKLIST_STATUS")
    private String blacklistStatus;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}