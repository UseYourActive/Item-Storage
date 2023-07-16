package com.example.storage.services;

import com.example.storage.api.operations.export.ExportStorageRequest;
import com.example.storage.api.operations.export.ExportStorageResponse;
import com.example.storage.api.operations.export.ExportStorageService;
import com.example.storage.data.entities.ItemStorage;
import com.example.storage.data.repositories.StorageRepository;
import com.example.storage.exceptions.NotEnoughQuantityOfSelectedItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ExportStorageServiceImpl implements ExportStorageService {
    private final StorageRepository storageRepository;

    @Override
    public ExportStorageResponse exportItem(ExportStorageRequest request) throws NotEnoughQuantityOfSelectedItemException {
        ItemStorage foundInRepo = findById(request.getId());

        if(foundInRepo.getQuantity() < request.getQuantity()){
            throw new NotEnoughQuantityOfSelectedItemException();
        }

        foundInRepo.setQuantity(foundInRepo.getQuantity() - request.getQuantity());

        ItemStorage save = storageRepository.save(foundInRepo);

        return ExportStorageResponse.builder()
                .id(save.getId())
                .quantity(save.getQuantity())
                .build();
    }

    private ItemStorage findById(UUID id) {
        return storageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
