package com.example.storage.api.operations.storageitem.find.allitemsbyid;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindAllStorageItemsByIdRequest implements OperationInput {
    private final List<String> itemIds;
}
