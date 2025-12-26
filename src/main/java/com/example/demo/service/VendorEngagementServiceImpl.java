package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;

import java.util.List;

public class VendorEngagementServiceImpl implements VendorEngagementService {

    private final VendorEngagementRecordRepository repository;
    private final PersonProfileRepository personRepository;

    public VendorEngagementServiceImpl(VendorEngagementRecordRepository repository,
                                       PersonProfileRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {

        PersonProfile emp = personRepository.findById(record.getEmployeeId())
                .orElseThrow(() -> new ApiException("Employee not found"));

        PersonProfile vendor = personRepository.findById(record.getVendorId())
                .orElseThrow(() -> new ApiException("Vendor not found"));

        return repository.save(record);
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
        return repository.findByVendorId(vendorId);
    }

    public List<VendorEngagementRecord> getAllEngagements() {
        return repository.findAll();
    }
}
