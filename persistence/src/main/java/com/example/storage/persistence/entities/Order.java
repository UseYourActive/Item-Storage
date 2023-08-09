package com.example.storage.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
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
    public Order(Set<OrderItem> items, LocalDateTime timestamp, UUID user, BigDecimal orderPrice) {
        this.items = items;
        this.timestamp = timestamp;
        this.user = user;
        this.orderPrice = orderPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Set<OrderItem> items;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private UUID user;

    @Column
    private BigDecimal orderPrice;
}
