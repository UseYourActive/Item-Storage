package com.example.storage.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "storage_items")
public class StorageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private UUID targetItemId;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity;
}
