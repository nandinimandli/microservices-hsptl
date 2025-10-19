package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.entity.PharmacyItem;
import com.example.pharmacyservice.service.PharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @PostMapping("/{warehouseName}")
    public PharmacyItem addItem(@RequestBody PharmacyItem item, @PathVariable String warehouseName) {
        return pharmacyService.addItem(item, warehouseName);
    }

    @GetMapping("/{warehouseName}")
    public List<PharmacyItem> getItems(@PathVariable String warehouseName) {
        return pharmacyService.getItemsByWarehouse(warehouseName);
    }

    @GetMapping("/item/{itemId}")
    public PharmacyItem getItemById(@PathVariable Long itemId) {
        return pharmacyService.getItemById(itemId);
    }

    @GetMapping
    public List<PharmacyItem> getAllItems() {
        return pharmacyService.getAllItems();
    }

    @PutMapping("/item/{itemId}")
    public PharmacyItem updateItem(@PathVariable Long itemId, @RequestBody PharmacyItem item) {
        return pharmacyService.updateItem(itemId, item);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        pharmacyService.removeItem(itemId);
    }
}
