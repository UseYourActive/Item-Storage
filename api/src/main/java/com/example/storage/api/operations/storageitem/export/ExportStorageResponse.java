package com.example.storage.api.operations.storageitem.export;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExportStorageResponse implements OperationResult {
    private String id;
    private String targetItem;
    private BigDecimal price;
    private Integer quantity;
}
