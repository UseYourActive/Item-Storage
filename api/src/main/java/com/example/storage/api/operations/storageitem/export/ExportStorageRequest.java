package com.example.storage.api.operations.storageitem.export;

import com.example.storage.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ExportStorageRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private final String itemId;

    @Positive
    private final Integer quantity;
}
