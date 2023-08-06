package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImportStorageItemOperationProcessor implements ImportStorageOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ImportStorageResponse process(ImportStorageRequest importStorageRequest) {
        StorageItem foundInRepo = storageRepository.findById(importStorageRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        foundInRepo.setQuantity(importStorageRequest.getQuantity() + foundInRepo.getQuantity());

        StorageItem save = storageRepository.save(foundInRepo);

        return ImportStorageResponse.builder()
                .id(save.getId())
                .targetItem(save.getId())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
