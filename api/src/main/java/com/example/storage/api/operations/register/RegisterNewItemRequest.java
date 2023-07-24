package com.example.storage.api.operations.register;

import com.example.storage.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterNewItemRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private UUID id;

    @Positive
    private BigDecimal price;

    @Positive
    private Integer quantity;
}
