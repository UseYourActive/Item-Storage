package com.example.storage.api.operations.storageitem.change.price;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangeStoragePriceResponse implements OperationResult {
    private UUID id;
    private UUID targetItem;
    private BigDecimal price;
    private Integer quantity;
}