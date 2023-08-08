package com.example.storage.persistence.repositories;

import com.example.storage.persistence.entities.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface StorageItemRepository extends JpaRepository<StorageItem, UUID> {
    Set<StorageItem> findAllByTargetItemIdIn(List<UUID> uuids);

    Boolean existsItemStorageByTargetItemId(UUID id);

    Optional<StorageItem> findStorageItemById(UUID id);
}
