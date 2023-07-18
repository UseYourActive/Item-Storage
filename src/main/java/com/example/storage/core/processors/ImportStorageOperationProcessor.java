package com.example.storage.core.processors;

import com.example.storage.api.operations.importing.ImportStorageRequest;
import com.example.storage.api.operations.importing.ImportStorageResponse;
import com.example.storage.api.operations.importing.ImportStorageOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImportStorageOperationProcessor implements ImportStorageOperation {
    private final StorageRepository storageRepository;

    @Override
    public ImportStorageResponse process(ImportStorageRequest importStorageRequest) {
        ItemStorage foundInRepo = storageRepository.findById(importStorageRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        foundInRepo.setQuantity(importStorageRequest.getQuantity() + foundInRepo.getQuantity());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ImportStorageResponse.builder()
                .item_id(save.getId())
                .quantity(save.getQuantity())
                .build();
    }
}
