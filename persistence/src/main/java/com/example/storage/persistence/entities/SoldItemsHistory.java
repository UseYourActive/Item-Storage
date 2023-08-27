package com.example.storage.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Table(name = "sold_items_history")
@Builder
public class SoldItemsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private UUID itemId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    @CreationTimestamp
    private Timestamp date;
}
