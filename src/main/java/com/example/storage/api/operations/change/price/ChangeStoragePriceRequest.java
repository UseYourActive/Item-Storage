package com.example.storage.api.operations.change.price;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangeStoragePriceRequest implements OperationInput {
    private UUID id;
    private BigDecimal price;
}
