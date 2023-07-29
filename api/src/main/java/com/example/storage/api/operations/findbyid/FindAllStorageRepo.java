package com.example.storage.api.operations.findbyid;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllStorageRepo {
    private UUID id;
    private UUID targetItem;
    private BigDecimal price;
    private Integer quantity;
}
