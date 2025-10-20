package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.entity.PharmacyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyItemRepository extends JpaRepository<PharmacyItem, Long> {
    List<PharmacyItem> findByPharmacyPharmacyName(String pharmacyName);
}
