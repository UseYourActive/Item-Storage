package com.example.storage.api.operations.order.place;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrderInputCartItem {
    private UUID referencedItemId;
    private BigDecimal price;
    private Integer quantity;
    private UUID userId;
}
