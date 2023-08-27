package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public RegisterNewItemResponse process(final RegisterNewItemRequest registerNewItemRequest) {
        log.info("Processing RegisterNewItemRequest");

        StorageItem item = StorageItem.builder()
                .targetItemId(UUID.fromString(registerNewItemRequest.getId()))
                .price(registerNewItemRequest.getPrice())
                .quantity(0)
                .build();
        log.info("Creating and saving new storage item in the repository");

        StorageItem save = storageRepository.save(item);
        log.info("New storage item created and saved in the repository with id: {}", save.getId());

        return RegisterNewItemResponse.builder()
                .id(String.valueOf(save.getId()))
                .itemId(String.valueOf(save.getTargetItemId()))
                .quantity(0)
                .price(save.getPrice())
                .build();
    }
}
