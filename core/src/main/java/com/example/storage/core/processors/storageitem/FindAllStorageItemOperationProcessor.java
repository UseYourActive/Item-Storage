package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemRequest;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageRepo;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllStorageItemOperationProcessor implements FindAllStorageItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public FindAllStorageItemResponse process(FindAllStorageItemRequest findAllStorageItemRequest) {
        return FindAllStorageItemResponse.builder()
                .items(storageRepository.findAll()
                        .stream()
                        .map(this::mapStorageItems)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllStorageRepo mapStorageItems(StorageItem itemStorage){
        return FindAllStorageRepo.builder()
                .id(itemStorage.getId())
                .targetItem(itemStorage.getTargetItemId())
                .price(itemStorage.getPrice())
                .quantity(itemStorage.getQuantity())
                .build();
    }
}
