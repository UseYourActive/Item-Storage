package com.example.storage.api.operations.register;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterNewItemRequest {
    private UUID id;
    private BigDecimal price;
    private Integer quantity;
}
