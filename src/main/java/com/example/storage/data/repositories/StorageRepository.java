package com.example.storage.data.repositories;

import com.example.storage.data.entities.ItemStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorageRepository extends JpaRepository<ItemStorage, UUID> {
}
