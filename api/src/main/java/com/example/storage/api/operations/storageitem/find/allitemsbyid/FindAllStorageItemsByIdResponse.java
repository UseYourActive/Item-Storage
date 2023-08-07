package com.example.storage.api.operations.storageitem.find.allitemsbyid;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageItemsByIdResponse implements OperationResult {
    private List<FindAllStorageItemsByIdInRepo> items;
}
