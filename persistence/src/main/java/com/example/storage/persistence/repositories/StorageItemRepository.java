package com.example.storage.persistence.repositories;

import com.example.storage.persistence.entities.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface StorageItemRepository extends JpaRepository<StorageItem, UUID> {
    Optional<StorageItem> findByTargetItemId(UUID itemId);
    List<StorageItem> findByTargetItemIdIn(Collection<UUID> ids);
}
