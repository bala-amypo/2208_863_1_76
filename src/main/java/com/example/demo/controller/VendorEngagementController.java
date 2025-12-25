package com.example.demo.controller;

import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.service.VendorEngagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engagements")
public class VendorEngagementController {

    private final VendorEngagementService service;

    public VendorEngagementController(VendorEngagementService service) {
        this.service = service;
    }

    @PostMapping
    public VendorEngagementRecord add(@RequestBody VendorEngagementRecord record) {
        return service.addEngagement(record);
    }

    @GetMapping
    public List<VendorEngagementRecord> getAll() {
        return service.getAllEngagements();
    }

    @GetMapping("/employee/{id}")
    public List<VendorEngagementRecord> getByEmployee(@PathVariable Long id) {
        return service.getEngagementsByEmployee(id);
    }

    @GetMapping("/vendor/{id}")
    public List<VendorEngagementRecord> getByVendor(@PathVariable Long id) {
        return service.getEngagementsByVendor(id);
    }
}
