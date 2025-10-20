package com.example.pharmacyservice.service;

import com.example.pharmacyservice.entity.Pharmacy;
import com.example.pharmacyservice.entity.PharmacyItem;
import com.example.pharmacyservice.repository.PharmacyItemRepository;
import com.example.pharmacyservice.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyItemRepository itemRepo;
    private final PharmacyRepository pharmacyRepo;

    public PharmacyService(PharmacyItemRepository itemRepo, PharmacyRepository pharmacyRepo) {
        this.itemRepo = itemRepo;
        this.pharmacyRepo = pharmacyRepo;
    }

    public PharmacyItem addItem(PharmacyItem item, String pharmacyName) {
        Pharmacy pharmacy = pharmacyRepo.findByPharmacyName(pharmacyName)
                .orElseGet(() -> {
                    Pharmacy newPharmacy = new Pharmacy();
                    newPharmacy.setName(pharmacyName); // corrected line
                    return pharmacyRepo.save(newPharmacy);
                });

        item.setPharmacy(pharmacy);
        return itemRepo.save(item);
    }

    public List<PharmacyItem> getItemsByPharmacy(String pharmacyName) {
        return itemRepo.findByPharmacyPharmacyName(pharmacyName);
    }

    public List<PharmacyItem> getAllItems() {
        return itemRepo.findAll();
    }

    public PharmacyItem getItemById(Long itemId) {
        return itemRepo.findById(itemId).orElse(null);
    }

    public PharmacyItem updateItem(Long itemId, PharmacyItem updatedItem) {
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
