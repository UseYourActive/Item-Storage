package com.example.storage.api.operations.export;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExportStorageRequest implements OperationInput {
    private UUID id;
    private Integer quantity;
}
