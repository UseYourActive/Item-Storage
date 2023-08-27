package com.example.storage.api.operations.storageitem.importing;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImportStorageResponse implements OperationResult {
    private String id;
    private String targetItem;
    private BigDecimal price;
    private Integer quantity;
}
