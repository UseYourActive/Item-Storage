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

@RequiredArgsConstructor
@Slf4j
@Service
public class FindStorageItemByIdOperationProcessor implements FindItemByIdOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public FindItemByIdResponse process(FindItemByIdRequest findItemByIdRequest) {
        log.info("Processing FindStorageItemByIdRequest");

        StorageItem itemStorage = storageRepository.findById(findItemByIdRequest.getId())
                .orElseThrow(() -> {
                    log.warn("Item not found in repository for id: {}", findItemByIdRequest.getId());
                    return new ItemNotFoundInRepositoryException();
                });
        log.info("Found storage item in the repository with id: {}", itemStorage.getId());

        return FindItemByIdResponse.builder()
                .id(itemStorage.getId())
                .referencedItemId(itemStorage.getTargetItemId())
                .quantity(itemStorage.getQuantity())
                .price(itemStorage.getPrice())
                .build();
    }
}
