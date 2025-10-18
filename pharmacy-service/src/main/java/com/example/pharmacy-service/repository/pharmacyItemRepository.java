package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByInventoryWarehouseName(String warehouseName);
}
