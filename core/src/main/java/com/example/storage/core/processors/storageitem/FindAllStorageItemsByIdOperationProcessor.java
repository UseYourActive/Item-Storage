package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdInRepo;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.core.exceptions.NotAllItemIdsFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllStorageItemsByIdOperationProcessor implements FindAllStorageItemsByIdOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public FindAllStorageItemsByIdResponse process(FindAllStorageItemsByIdRequest findAllStorageItemsByIdRequest) {
        Set<StorageItem> storageItems = new HashSet<>(this.storageItemRepository.findAllById(findAllStorageItemsByIdRequest.getItemIds()));

        if(storageItems.size() != findAllStorageItemsByIdRequest.getItemIds().size()){
            throw new NotAllItemIdsFoundInRepositoryException();
        }

        return FindAllStorageItemsByIdResponse.builder()
                .items(storageItems.stream()
                        .map(this::mapStorageItems)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllStorageItemsByIdInRepo mapStorageItems(StorageItem storageItem){
        return FindAllStorageItemsByIdInRepo.builder()
                .id(storageItem.getId())
                .referencedItemId(storageItem.getTargetItemId())
                .price(storageItem.getPrice())
                .quantity(storageItem.getQuantity())
                .build();
    }
}