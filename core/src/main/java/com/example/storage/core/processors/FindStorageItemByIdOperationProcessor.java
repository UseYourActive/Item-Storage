package com.example.storage.core.processors;

import com.example.storage.api.operations.findbyid.FindItemByIdOperation;
import com.example.storage.api.operations.findbyid.FindItemByIdRequest;
import com.example.storage.api.operations.findbyid.FindItemByIdResponse;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindStorageItemByIdOperationProcessor implements FindItemByIdOperation {
    private final StorageRepository storageRepository;
    @Override
    public FindItemByIdResponse process(FindItemByIdRequest findItemByIdRequest) {
        ItemStorage itemStorage = storageRepository.findByTargetItem(findItemByIdRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        return FindItemByIdResponse.builder()
                .id(itemStorage.getId())
                .referencedItemId(itemStorage.getTargetItem())
                .quantity(itemStorage.getQuantity())
                .price(itemStorage.getPrice())
                .build();
    }
}
