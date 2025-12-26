package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.repository.VendorEngagementRepository;
import com.example.demo.service.VendorEngagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorEngagementServiceImpl implements VendorEngagementService {

    private final VendorEngagementRepository repository;

    public VendorEngagementServiceImpl(VendorEngagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {

        if (record.getEmployeeId() == null) {
            throw new ApiException("Employee not found");
        }

        return repository.save(record);
    }

    @Override
    public VendorEngagementRecord getEngagementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Engagement not found"));
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return repository.findAll();
    }
}
