package com.example.storage.api.operations.storageitem.sell;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StorageItemsSellRequest implements OperationInput {
    private UUID userId;

    private List<ItemSellDataResponse> items;
}
