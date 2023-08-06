package com.example.storage.api.operations.storageitem.create;

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
public class CreateStorageItemRequest implements OperationInput {
    @NotNull(message = "Target item id is required!")
    private UUID targetItemId;

    @Positive(message = "Price must be a positive number!")
    private BigDecimal price;
}
