package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByWarehouseName(String warehouseName);
}
