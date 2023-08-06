package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExportStorageItemOperationProcessor implements ExportStorageOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ExportStorageResponse process(ExportStorageRequest exportStorageRequest) {
        StorageItem foundInRepo = storageRepository.findById(exportStorageRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        foundInRepo.setQuantity(foundInRepo.getQuantity() - exportStorageRequest.getQuantity());

        StorageItem save = storageRepository.save(foundInRepo);

        return ExportStorageResponse.builder()
                .id(save.getId())
                .targetItem(save.getTargetItemId())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
