package com.example.storage.api.operations.storageitem.sell;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemSellDataResponse {
    private String itemId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
}
