package com.example.storage.core.processors;

import com.example.storage.api.operations.register.RegisterNewItemRequest;
import com.example.storage.api.operations.register.RegisterNewItemResponse;
import com.example.storage.api.operations.register.RegisterNewItemOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import com.example.zoostore.restexport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterNewItemOperationProcessor implements RegisterNewItemOperation {
    private final StorageRepository storageRepository;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public RegisterNewItemResponse process(RegisterNewItemRequest registerNewItemRequest) {
        try {
            zooStoreRestClient.getItemById(registerNewItemRequest.getId().toString());
        }catch (Exception e){
            throw new ItemNotFoundInRepositoryException("Ne uspqh da namerq item s takova id v drugata baza");
        }

        ItemStorage item = ItemStorage.builder()
                .targetItem(registerNewItemRequest.getId())
                .price(registerNewItemRequest.getPrice())
                .quantity(registerNewItemRequest.getQuantity())
                .build();

        ItemStorage save = storageRepository.save(item);

        return RegisterNewItemResponse.builder()
                .id(save.getId())
                .item_id(save.getTargetItem())
                .quantity(save.getQuantity())
                .price(save.getPrice())
                .build();
    }
}
