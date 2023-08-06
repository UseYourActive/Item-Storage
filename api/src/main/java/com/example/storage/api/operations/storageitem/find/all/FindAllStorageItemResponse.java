package com.example.storage.api.operations.storageitem.find.all;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageItemResponse implements OperationResult {
    private Set<FindAllStorageRepo> items;
}
