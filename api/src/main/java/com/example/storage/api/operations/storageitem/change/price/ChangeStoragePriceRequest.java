package com.example.storage.api.operations.storageitem.change.price;

import com.example.storage.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ChangeStoragePriceRequest implements OperationInput {
    @NotNull(message = "Vendor UUID is required!")
    private final String id;

    @Positive
    private final BigDecimal price;
}
