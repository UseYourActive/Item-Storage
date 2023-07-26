package com.example.storage.persistence.repositories;

import com.example.storage.persistence.entities.ItemStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<ItemStorage, UUID> {
    Optional<ItemStorage> findByTargetItem(UUID item_id);
}
