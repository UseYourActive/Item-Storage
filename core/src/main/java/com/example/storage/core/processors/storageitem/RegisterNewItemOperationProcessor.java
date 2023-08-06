package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import com.example.zoostore.restexport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageItemRepository storageRepository;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public RegisterNewItemResponse process(RegisterNewItemRequest registerNewItemRequest) {
        try {
            zooStoreRestClient.getItemById(registerNewItemRequest.getId().toString());
        }catch (Exception e){
            throw new ItemNotFoundInRepositoryException("Ne uspqh da namerq item s takova id v drugata baza");
        }

        StorageItem item = StorageItem.builder()
                .targetItemId(registerNewItemRequest.getId())
                .price(registerNewItemRequest.getPrice())
                .quantity(registerNewItemRequest.getQuantity())
                .build();

        StorageItem save = storageRepository.save(item);

        return RegisterNewItemResponse.builder()
                .id(save.getId())
                .item_id(save.getTargetItemId())
                .quantity(save.getQuantity())
                .price(save.getPrice())
                .build();
    }
}
