package com.vms.vms_backend.controller;

import com.vms.vms_backend.entity.Visitor;
import com.vms.vms_backend.repository.VisitorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorRepository visitorRepository;

    public VisitorController(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    // GET all visitors
    @GetMapping
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    // POST - Register Visitor
    @PostMapping
    public Visitor registerVisitor(@RequestBody Visitor visitor) {
        visitor.setBlacklistStatus("NO");
        return visitorRepository.save(visitor);
    }
}