package com.example.storage.api.operations.storageitem.find.allitemsbyid;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageItemsByIdInRepo {
    private UUID id;
    private UUID referencedItemId;
    private BigDecimal price;
    private Integer quantity;
}
