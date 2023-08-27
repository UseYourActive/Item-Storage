package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImportStorageItemOperationProcessor implements ImportStorageOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ImportStorageResponse process(final ImportStorageRequest importStorageRequest) {
        log.info("Processing ImportStorageRequest");

        StorageItem foundInRepo = storageRepository.findById(UUID.fromString(importStorageRequest.getId()))
                .orElseThrow(() -> {
                    log.warn("Item not found in repository for id: {}", importStorageRequest.getId());
                    return new ItemNotFoundInRepositoryException();
                });
        log.info("Found storage item in the repository with id: {}", foundInRepo.getId());

        foundInRepo.setQuantity(importStorageRequest.getQuantity() + foundInRepo.getQuantity());

        StorageItem save = storageRepository.save(foundInRepo);
        log.info("Updated storage item in the repository with id: {}", save.getId());

        return ImportStorageResponse.builder()
                .id(String.valueOf(save.getId()))
                .targetItem(String.valueOf(save.getId()))
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
