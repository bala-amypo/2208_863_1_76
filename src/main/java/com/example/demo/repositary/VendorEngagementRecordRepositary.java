package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.VendorEngagementRecord;

public interface VendorEngagementRecordRepository
        extends JpaRepository<VendorEngagementRecord, Long> {

    List<VendorEngagementRecord> findByEmployeeId(Long employeeId);

    List<VendorEngagementRecord> findByVendorId(Long vendorId);
}
