package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Optional<Pharmacy> findByPharmacyName(String pharmacyName);
}
