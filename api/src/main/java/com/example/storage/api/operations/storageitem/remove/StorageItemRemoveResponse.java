package com.example.storage.api.operations.storageitem.remove;

import com.example.storage.api.base.OperationResult;
import lombok.*;

@Getter
@Builder
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StorageItemRemoveResponse implements OperationResult {
    private String result;
}
