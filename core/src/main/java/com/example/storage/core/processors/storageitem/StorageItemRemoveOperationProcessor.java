package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveOperation;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveRequest;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveResponse;
import com.example.storage.core.exceptions.StorageItemNotFoundException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class StorageItemRemoveOperationProcessor implements StorageItemRemoveOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public StorageItemRemoveResponse process(StorageItemRemoveRequest storageItemRemoveRequest) {
        log.info("Processing StorageItemRemoveRequest for item with id: {}", storageItemRemoveRequest.getId());

        StorageItem storageItem = storageItemRepository.findById(storageItemRemoveRequest.getId())
                .orElseThrow(StorageItemNotFoundException::new);
        log.info("Removing storage item from the repository with id: {}", storageItem.getId());

        this.storageItemRepository.delete(storageItem);
        log.info("Storage item removed successfully");

        return StorageItemRemoveResponse.builder()
                .result("Successfully removed item!")
                .build();
    }
}
