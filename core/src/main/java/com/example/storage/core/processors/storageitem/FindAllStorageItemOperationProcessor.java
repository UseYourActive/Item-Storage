package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemRequest;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageRepo;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllStorageItemOperationProcessor implements FindAllStorageItemOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public FindAllStorageItemResponse process(FindAllStorageItemRequest findAllStorageItemRequest) {
        log.info("Processing FindAllStorageItemRequest");

        List<FindAllStorageRepo> storageItems = storageRepository.findAll()
                .stream()
                .map(this::mapStorageItems)
                .toList();
        log.info("Found {} storage items in the repository", storageItems.size());

        return FindAllStorageItemResponse.builder()
                .items(storageItems)
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
