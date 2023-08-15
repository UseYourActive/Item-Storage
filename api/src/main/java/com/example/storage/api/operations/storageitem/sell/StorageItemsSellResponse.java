package com.example.storage.api.operations.storageitem.sell;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StorageItemsSellResponse implements OperationResult {
    private List<ItemSellDataResponse> items;
}
