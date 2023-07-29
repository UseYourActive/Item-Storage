package com.example.storage.core.processors;

import com.example.storage.api.operations.findall.FindAllStorageItemOperation;
import com.example.storage.api.operations.findall.FindAllStorageItemRequest;
import com.example.storage.api.operations.findall.FindAllStorageItemResponse;
import com.example.storage.api.operations.findbyid.FindAllStorageRepo;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllStorageItemOperationProcessor implements FindAllStorageItemOperation {
    private final StorageRepository storageRepository;

    @Override
    public FindAllStorageItemResponse process(FindAllStorageItemRequest findAllStorageItemRequest) {
        return FindAllStorageItemResponse.builder()
                .items(storageRepository.findAll()
                        .stream()
                        .map(this::mapStorageItems)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllStorageRepo mapStorageItems(ItemStorage itemStorage){
        return FindAllStorageRepo.builder()
                .id(itemStorage.getId())
                .targetItem(itemStorage.getTargetItem())
                .price(itemStorage.getPrice())
                .quantity(itemStorage.getQuantity())
                .build();
    }
}
