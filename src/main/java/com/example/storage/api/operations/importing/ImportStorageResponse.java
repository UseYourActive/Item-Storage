package com.example.storage.api.operations.importing;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImportStorageResponse implements OperationResult {
    private UUID item_id;
    private Integer quantity;
}
