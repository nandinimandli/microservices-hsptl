package com.example.pharmacyservice.service;

import com.example.pharmacyservice.entity.Inventory;
import com.example.pharmacyservice.entity.InventoryItem;
import com.example.pharmacyservice.repository.InventoryItemRepository;
import com.example.pharmacyservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryItemRepository itemRepo;
    private final InventoryRepository inventoryRepo;

    public InventoryService(InventoryItemRepository itemRepo, InventoryRepository inventoryRepo) {
        this.itemRepo = itemRepo;
        this.inventoryRepo = inventoryRepo;
    }

    public InventoryItem addItem(InventoryItem item, String warehouseName) {
        Inventory inventory = inventoryRepo.findByWarehouseName(warehouseName)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setWarehouseName(warehouseName);
                    return inventoryRepo.save(newInventory);
                });

        item.setInventory(inventory);
        return itemRepo.save(item);
    }

    public List<InventoryItem> getItemsByWarehouse(String warehouseName) {
        return itemRepo.findByInventoryWarehouseName(warehouseName);
    }

    public List<InventoryItem> getAllItems() {
        return itemRepo.findAll();
    }

    public InventoryItem getItemById(Long itemId) {
        return itemRepo.findById(itemId).orElse(null);
    }

    public InventoryItem updateItem(Long itemId, InventoryItem updatedItem) {
        return itemRepo.findById(itemId).map(item -> {
            item.setProductId(updatedItem.getProductId());
            item.setStock(updatedItem.getStock());
            return itemRepo.save(item);
        }).orElse(null);
    }

    public void removeItem(Long itemId) {
        itemRepo.deleteById(itemId);
    }
}
