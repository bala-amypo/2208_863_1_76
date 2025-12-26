package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorEngagementServiceImpl implements VendorEngagementService {

    private final VendorEngagementRecordRepository repository;

    public VendorEngagementServiceImpl(VendorEngagementRecordRepository repository) {
        this.repository = repository;
    }

    // ✅ REQUIRED by interface
    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {

        if (record.getEmployeeId() == null) {
            throw new ApiException("Employee not found");
        }

        return repository.save(record);
    }

    // ✅ REQUIRED by interface
    @Override
    public VendorEngagementRecord getEngagementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Engagement not found"));
    }

    // ✅ REQUIRED by interface
    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .toList();
    }

    // ✅ REQUIRED by interface
    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getVendorId().equals(vendorId))
                .toList();
    }

    // ✅ REQUIRED by interface
    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return repository.findAll();
    }
}
