package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.VendorEngagementRecord;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.VendorEngagementRecordRepository;
import com.example.demo.service.VendorEngagementService;

@Service
public class VendorEngagementServiceImpl implements VendorEngagementService {

    private final VendorEngagementRecordRepository engagementRepository;
    private final PersonProfileRepository personProfileRepository;

    public VendorEngagementServiceImpl(
            VendorEngagementRecordRepository engagementRepository,
            PersonProfileRepository personProfileRepository) {

        this.engagementRepository = engagementRepository;
        this.personProfileRepository = personProfileRepository;
    }

    @Override
    public VendorEngagementRecord addEngagement(VendorEngagementRecord record) {

       
        personProfileRepository.findById(record.getEmployeeId())
                .orElseThrow(() -> new ApiException("person not found"));

      
        personProfileRepository.findById(record.getVendorId())
                .orElseThrow(() -> new ApiException("person not found"));

        
        return engagementRepository.save(record);
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByEmployee(Long employeeId) {
        return engagementRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<VendorEngagementRecord> getEngagementsByVendor(Long vendorId) {
        return engagementRepository.findByVendorId(vendorId);
    }

    @Override
    public List<VendorEngagementRecord> getAllEngagements() {
        return engagementRepository.findAll();
    }
}
