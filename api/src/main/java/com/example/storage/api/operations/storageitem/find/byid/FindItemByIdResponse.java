package com.example.storage.api.operations.storageitem.find.byid;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemByIdResponse implements OperationResult {
    private String id;
    private String referencedItemId;
    private BigDecimal price;
    private Integer quantity;
}