package com.example.storage.services;

import com.example.storage.api.operations.importing.ImportStorageRequest;
import com.example.storage.api.operations.importing.ImportStorageResponse;
import com.example.storage.api.operations.importing.ImportStorageService;
import com.example.storage.data.entities.ItemStorage;
import com.example.storage.data.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImportStorageServiceImpl implements ImportStorageService {
    private final StorageRepository storageRepository;

    @Override
    public ImportStorageResponse importItem(ImportStorageRequest request) {
        ItemStorage foundInRepo = findById(request.getId());

        foundInRepo.setQuantity(request.getQuantity() + foundInRepo.getQuantity());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ImportStorageResponse.builder()
                .item_id(save.getId())
                .quantity(save.getQuantity())
                .build();
    }

    private ItemStorage findById(UUID id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
