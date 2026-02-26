package com.vms.vms_backend.repository;

import com.vms.vms_backend.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}