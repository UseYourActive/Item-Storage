package com.example.storage.core.processors;

import com.example.storage.api.operations.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.change.price.ChangeStoragePriceOperation;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChangeStoragePriceOperationProcessor implements ChangeStoragePriceOperation {
    private final StorageRepository storageRepository;

    @Override
    public ChangeStoragePriceResponse process(ChangeStoragePriceRequest request) {
        ItemStorage foundInRepo = findById(request.getId());

        foundInRepo.setPrice(request.getPrice());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ChangeStoragePriceResponse.builder()
                .item_id(save.getItem_id())
                .price(save.getPrice())
                .build();
    }

    private ItemStorage findById(UUID id) {
        return storageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}