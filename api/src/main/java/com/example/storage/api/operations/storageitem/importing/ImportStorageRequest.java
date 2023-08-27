package com.example.storage.api.operations.storageitem.importing;

import com.example.storage.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImportStorageRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private String id;

    @Positive
    private Integer quantity;
}
