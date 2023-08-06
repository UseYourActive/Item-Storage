package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.create.CreateStorageItemOperation;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemRequest;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemResponse;
import com.example.storage.core.exceptions.ItemAlreadyExistsInTheItemStorageException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateStorageItemOperationProcessor implements CreateStorageItemOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public CreateStorageItemResponse process(CreateStorageItemRequest createStorageItemRequest) {
        Boolean isExistingItem = this.storageItemRepository.existsItemStorageByTargetItemId(createStorageItemRequest.getTargetItemId());

        if(isExistingItem){
            throw new ItemAlreadyExistsInTheItemStorageException();
        }

        StorageItem storageItem = StorageItem.builder()
                .targetItemId(createStorageItemRequest.getTargetItemId())
                .price(createStorageItemRequest.getPrice())
                .build();

        StorageItem save = this.storageItemRepository.save(storageItem);

        return CreateStorageItemResponse.builder()
                .id(save.getId())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .targetItem(save.getTargetItemId())
                .build();
    }
}
