package com.example.storage.api.operations.findbyid;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemByIdResponse implements OperationResult {
    private UUID id;
    private UUID referencedItemId;
    private BigDecimal price;
    private Integer quantity;
}