package com.example.demo.controller;

import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.service.VendorEngagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engagements")
public class VendorEngagementController {

    private final VendorEngagementService service;

    public VendorEngagementController(VendorEngagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VendorEngagementRecord> create(
            @RequestBody VendorEngagementRecord record) {
        return ResponseEntity.ok(service.createEngagement(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorEngagementRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEngagementById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorEngagementRecord>> getAll() {
        return ResponseEntity.ok(service.getAllEngagements());
    }
}
