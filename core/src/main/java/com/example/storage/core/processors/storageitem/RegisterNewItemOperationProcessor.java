package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public RegisterNewItemResponse process(RegisterNewItemRequest registerNewItemRequest) {
        StorageItem item = StorageItem.builder()
                .targetItemId(registerNewItemRequest.getId())
                .price(registerNewItemRequest.getPrice())
                .quantity(0)
                .build();

        StorageItem save = storageRepository.save(item);

        return RegisterNewItemResponse.builder()
                .id(save.getId())
                .item_id(save.getTargetItemId())
                .quantity(0)
                .price(save.getPrice())
                .build();
    }
}
