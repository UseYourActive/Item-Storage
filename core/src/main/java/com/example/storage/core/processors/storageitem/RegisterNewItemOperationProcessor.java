package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public RegisterNewItemResponse process(RegisterNewItemRequest registerNewItemRequest) {
        log.info("Processing RegisterNewItemRequest");

        StorageItem item = StorageItem.builder()
                .targetItemId(registerNewItemRequest.getId())
                .price(registerNewItemRequest.getPrice())
                .quantity(0)
                .build();
        log.info("Creating and saving new storage item in the repository");

        StorageItem save = storageRepository.save(item);
        log.info("New storage item created and saved in the repository with id: {}", save.getId());

        return RegisterNewItemResponse.builder()
                .id(save.getId())
                .item_id(save.getTargetItemId())
                .quantity(0)
                .price(save.getPrice())
                .build();
    }
}
