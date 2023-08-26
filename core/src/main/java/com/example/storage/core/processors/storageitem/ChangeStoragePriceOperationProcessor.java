package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChangeStoragePriceOperationProcessor implements ChangeStoragePriceOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ChangeStoragePriceResponse process(ChangeStoragePriceRequest request) {
        log.info("Processing ChangeStoragePriceRequest for item with ID: {}", request.getId());

        StorageItem foundInRepo = storageRepository.findById(request.getId())
                .orElseThrow(() -> {
                    log.error("Item with ID {} not found in repository.", request.getId());
                    return new ItemNotFoundInRepositoryException();
                });

        foundInRepo.setPrice(request.getPrice());

        StorageItem save = storageRepository.save(foundInRepo);
        log.info("StorageItem price changed successfully for item with ID: {}", save.getId());

        return ChangeStoragePriceResponse.builder()
                .id(save.getId())
                .targetItem(save.getTargetItemId())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
