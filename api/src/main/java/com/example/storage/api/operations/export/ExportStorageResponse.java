package com.example.storage.api.operations.export;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExportStorageResponse implements OperationResult {
    private UUID id;
    private UUID item_id;
    private Integer quantity;
}
