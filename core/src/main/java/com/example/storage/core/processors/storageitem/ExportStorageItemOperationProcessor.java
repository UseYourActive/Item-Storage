package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageItemOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.core.exceptions.StorageItemIllegalQuantityException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExportStorageItemOperationProcessor implements ExportStorageItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ExportStorageResponse process(final ExportStorageRequest exportStorageRequest) {
        log.info("Processing ExportStorageRequest for storage item with ID: {}", exportStorageRequest.getItemId());

        StorageItem foundInRepo = storageRepository.findById(UUID.fromString(exportStorageRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Integer originalQuantity = foundInRepo.getQuantity();
        Integer exportedQuantity = exportStorageRequest.getQuantity();
        Integer newQuantity = originalQuantity - exportedQuantity;
        log.info("Original quantity: {}, Exported quantity: {}, New quantity: {}", originalQuantity, exportedQuantity, newQuantity);

        if (newQuantity < 0) {
            log.warn("Storage item with ID {} has insufficient quantity for export.", exportStorageRequest.getItemId());
            throw new StorageItemIllegalQuantityException();
        }

        foundInRepo.setQuantity(newQuantity);

        StorageItem save = storageRepository.save(foundInRepo);
        log.info("Exported storage item with ID {}. New quantity: {}", save.getId(), save.getQuantity());

        return ExportStorageResponse.builder()
                .id(String.valueOf(save.getId()))
                .targetItem(String.valueOf(save.getTargetItemId()))
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
