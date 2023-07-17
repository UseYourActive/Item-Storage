package com.example.storage.api.operations.register;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterNewItemResponse implements OperationResult {
    private UUID item_id;
    private BigDecimal price;
    private Integer quantity;
}