package com.example.storage.core.processors;

import com.example.storage.api.operations.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.change.price.ChangeStoragePriceOperation;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeStoragePriceOperationProcessor implements ChangeStoragePriceOperation {
    private final StorageRepository storageRepository;

    @Override
    public ChangeStoragePriceResponse process(ChangeStoragePriceRequest request) {
        ItemStorage foundInRepo = storageRepository.findById(request.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        foundInRepo.setPrice(request.getPrice());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ChangeStoragePriceResponse.builder()
                .item_id(save.getTargetItem())
                .price(save.getPrice())
                .build();
    }
}
