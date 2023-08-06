package com.example.storage.api.operations.order.place;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrderSingleItem {
    private UUID id;
    private UUID referencedItemId;
    private Double price;
    private Integer quantity;
    private LocalDateTime timestamp;
    private UUID userId;
}
