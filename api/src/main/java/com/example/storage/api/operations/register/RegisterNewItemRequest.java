package com.example.storage.api.operations.register;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterNewItemRequest implements OperationInput {
    private UUID id;
    private BigDecimal price;
    private Integer quantity;
}
