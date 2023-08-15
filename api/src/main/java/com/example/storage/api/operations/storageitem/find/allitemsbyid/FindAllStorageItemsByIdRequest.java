package com.example.storage.api.operations.storageitem.find.allitemsbyid;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageItemsByIdRequest implements OperationInput {
    private List<UUID> itemIds;
}
