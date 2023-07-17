package com.example.storage.api.operations.change.price;

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
    private UUID item_id;
    private BigDecimal price;
}
