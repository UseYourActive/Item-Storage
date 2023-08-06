package com.example.storage.api.operations.storageitem.find.allitemsbyid;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageItemsByIdRequest implements OperationInput {
    private Set<UUID> itemIds;
}
