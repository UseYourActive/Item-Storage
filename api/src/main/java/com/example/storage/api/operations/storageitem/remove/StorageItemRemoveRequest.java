package com.example.storage.api.operations.storageitem.remove;

import com.example.storage.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StorageItemRemoveRequest implements OperationInput {
    private final String id;
}
