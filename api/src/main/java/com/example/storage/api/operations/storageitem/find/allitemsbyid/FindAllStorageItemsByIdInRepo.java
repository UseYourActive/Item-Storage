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
    private String id;
    private String referencedItemId;
    private BigDecimal price;
    private Integer quantity;
}
