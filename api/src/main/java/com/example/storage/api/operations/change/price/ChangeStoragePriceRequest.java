package com.example.storage.api.operations.change.price;

import com.example.storage.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangeStoragePriceRequest implements OperationInput {
    @NotBlank(message = "Vendor UUID is required!")
    private UUID id;

    @Positive
    private BigDecimal price;
}
