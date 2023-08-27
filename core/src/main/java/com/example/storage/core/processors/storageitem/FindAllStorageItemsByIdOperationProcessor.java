package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdInRepo;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.core.exceptions.NotAllItemIdsFoundInRepositoryException;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllStorageItemsByIdOperationProcessor implements FindAllStorageItemsByIdOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public FindAllStorageItemsByIdResponse process(final FindAllStorageItemsByIdRequest findAllStorageItemsByIdRequest) {
        log.info("Processing FindAllStorageItemsByIdRequest");

        List<UUID> ids = findAllStorageItemsByIdRequest.getItemIds().stream()
                .map(UUID::fromString)
                .toList();

        List<StorageItem> storageItems = this.storageItemRepository.findByTargetItemIdIn(ids);

        if (storageItems.size() != findAllStorageItemsByIdRequest.getItemIds().size()) {
            log.warn("Not all item ids found in repository");
            throw new NotAllItemIdsFoundInRepositoryException();
        }
        log.info("Found {} storage items in the repository", storageItems.size());

        return FindAllStorageItemsByIdResponse.builder()
                .items(storageItems.stream()
                        .map(this::mapStorageItems)
                        .toList())
                .build();
    }

    private FindAllStorageItemsByIdInRepo mapStorageItems(StorageItem storageItem){
        return FindAllStorageItemsByIdInRepo.builder()
                .id(String.valueOf(storageItem.getId()))
                .referencedItemId(String.valueOf(storageItem.getTargetItemId()))
                .price(storageItem.getPrice())
                .quantity(storageItem.getQuantity())
                .build();
    }
}
