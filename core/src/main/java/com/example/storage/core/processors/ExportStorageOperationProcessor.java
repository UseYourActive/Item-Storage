package com.example.storage.core.processors;

import com.example.storage.api.operations.export.ExportStorageRequest;
import com.example.storage.api.operations.export.ExportStorageResponse;
import com.example.storage.api.operations.export.ExportStorageOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import com.example.storage.core.exceptions.NotEnoughQuantityOfSelectedItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExportStorageOperationProcessor implements ExportStorageOperation {
    private final StorageRepository storageRepository;

    @Override
    public ExportStorageResponse process(ExportStorageRequest exportStorageRequest) {
        ItemStorage foundInRepo = storageRepository.findById(exportStorageRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        if(foundInRepo.getQuantity() < exportStorageRequest.getQuantity()){
            throw new NotEnoughQuantityOfSelectedItemException();
        }

        foundInRepo.setQuantity(foundInRepo.getQuantity() - exportStorageRequest.getQuantity());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ExportStorageResponse.builder()
                .id(save.getId())
                .quantity(save.getQuantity())
                .build();
    }
}
