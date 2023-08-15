package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveOperation;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveRequest;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveResponse;
import com.example.storage.core.exceptions.StorageItemNotFoundException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StorageItemRemoveOperationProcessor implements StorageItemRemoveOperation {
    private final StorageItemRepository storageItemRepository;


    @Override
    public StorageItemRemoveResponse process(StorageItemRemoveRequest storageItemRemoveRequest) {
        StorageItem storageItem = storageItemRepository.findById(storageItemRemoveRequest.getId())
                .orElseThrow(StorageItemNotFoundException::new);

        this.storageItemRepository.delete(storageItem);

        return StorageItemRemoveResponse.builder()
                .result("Successfully removed item!")
                .build();
    }
}
