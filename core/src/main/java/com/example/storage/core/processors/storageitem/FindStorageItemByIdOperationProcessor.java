package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdOperation;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdRequest;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
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
public class FindStorageItemByIdOperationProcessor implements FindItemByIdOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public FindItemByIdResponse process(final FindItemByIdRequest findItemByIdRequest) {
        log.info("Processing FindStorageItemByIdRequest");

        StorageItem storageItem = this.storageRepository.findByTargetItemId(UUID.fromString(findItemByIdRequest.getId()))
                .orElseThrow(() -> {
                    log.warn("Item not found in repository for id: {}", findItemByIdRequest.getId());
                    return new ItemNotFoundInRepositoryException();
                });
        log.info("Found storage item in the repository with id: {}", storageItem.getId());

        return FindItemByIdResponse.builder()
                .id(String.valueOf(storageItem.getId()))
                .referencedItemId(String.valueOf(storageItem.getTargetItemId()))
                .quantity(storageItem.getQuantity())
                .price(storageItem.getPrice())
                .build();
    }
}
