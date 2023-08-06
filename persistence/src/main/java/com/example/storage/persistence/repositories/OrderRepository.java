package com.example.storage.persistence.repositories;

import com.example.storage.persistence.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
