package com.example.storage.persistence.repositories;

import com.example.storage.persistence.entities.SoldItemsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SoldItemsHistoryRepository extends JpaRepository<SoldItemsHistory, UUID> {
    Boolean existsByUserId(UUID userId);
}
