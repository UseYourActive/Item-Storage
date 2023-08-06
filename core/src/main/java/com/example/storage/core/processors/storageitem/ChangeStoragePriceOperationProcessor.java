package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeStoragePriceOperationProcessor implements ChangeStoragePriceOperation {
    private final StorageItemRepository storageRepository;

    @Override
    public ChangeStoragePriceResponse process(ChangeStoragePriceRequest request) {
        StorageItem foundInRepo = storageRepository.findById(request.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        foundInRepo.setPrice(request.getPrice());

        StorageItem save = storageRepository.save(foundInRepo);

        return ChangeStoragePriceResponse.builder()
                .id(save.getId())
                .targetItem(save.getTargetItemId())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .build();
    }
}
