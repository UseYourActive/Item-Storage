package com.example.storage.api.operations.storageitem.find.all;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageRepo {
    private String id;
    private String targetItem;
    private BigDecimal price;
    private Integer quantity;
}
