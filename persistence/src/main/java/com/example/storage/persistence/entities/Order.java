package com.example.storage.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Builder
    public Order(UUID referencedItemId, BigDecimal price, Integer quantity, LocalDateTime timestamp, UUID user) {
        this.referencedItemId = referencedItemId;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID referencedItemId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private UUID user;
}
