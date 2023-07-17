package com.example.storage.core.processors;

import com.example.storage.api.operations.register.RegisterNewItemRequest;
import com.example.storage.api.operations.register.RegisterNewItemResponse;
import com.example.storage.api.operations.register.RegisterNewItemOperation;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageRepository storageRepository;

    @Override
    public RegisterNewItemResponse registerNewItem(RegisterNewItemRequest request) {
        ItemStorage item = ItemStorage.builder()
                .item_id(request.getId())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        ItemStorage save = storageRepository.save(item);

        return RegisterNewItemResponse.builder()
                .item_id(save.getItem_id())
                .quantity(save.getQuantity())
                .price(save.getPrice())
                .build();
    }
}
