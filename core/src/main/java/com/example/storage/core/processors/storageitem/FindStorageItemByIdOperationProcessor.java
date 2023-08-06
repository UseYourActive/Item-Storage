package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdOperation;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdRequest;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindStorageItemByIdOperationProcessor implements FindItemByIdOperation {
    private final StorageItemRepository storageRepository;
    @Override
    public FindItemByIdResponse process(FindItemByIdRequest findItemByIdRequest) {
        StorageItem itemStorage = storageRepository.findById(findItemByIdRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        return FindItemByIdResponse.builder()
                .id(itemStorage.getId())
                .referencedItemId(itemStorage.getTargetItemId())
                .quantity(itemStorage.getQuantity())
                .price(itemStorage.getPrice())
                .build();
    }
}
